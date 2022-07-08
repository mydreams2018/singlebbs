package cn.kungreat.singlebbs.security;

import cn.kungreat.singlebbs.SinglebbsApplication;
import cn.kungreat.singlebbs.domain.Oauth2User;
import cn.kungreat.singlebbs.domain.User;
import cn.kungreat.singlebbs.service.Oauth2UserService;
import cn.kungreat.singlebbs.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.*;
@Component
public class MyOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Autowired
    private Oauth2UserService oauth2UserService;
    @Autowired
    private PermissionService permissionService;

    private RestOperations restOperations;
    {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        this.restOperations = restTemplate;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Assert.notNull(userRequest, "userRequest cannot be null");
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        Map<String, Object> additionalParameters = userRequest.getAdditionalParameters();
        String tokenValue = userRequest.getAccessToken().getTokenValue();
        ResponseEntity<String> response;
        Map<String,Object> userAttributes;
        String openid ;
        try {
            ResponseEntity<String> forEntity = this.restOperations.getForEntity("https://graph.qq.com/oauth2.0/me?access_token="
                    + tokenValue, String.class);
            String body = forEntity.getBody();
            String replace = body.substring(9,body.length()-4);
            Map<String,Object> stringObjectMap = SinglebbsApplication.MAP_JSON.readValue(replace,Map.class);
            openid = stringObjectMap.get("openid").toString();
            response = this.restOperations.getForEntity(clientRegistration.getProviderDetails().getUserInfoEndpoint().getUri()+"?access_token="
                    +tokenValue+"&oauth_consumer_key="+clientRegistration.getClientId()+"&openid="+ openid,String.class);
            userAttributes = SinglebbsApplication.MAP_JSON.readValue(response.getBody(),Map.class);
        }catch(Exception ex){
            OAuth2Error oauth2Error = new OAuth2Error("invalid_user_info_response",
                    "An error occurred while attempting to retrieve the UserInfo Resource: " + ex.getMessage(), null);
            throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString(),ex);
        }
        User user = oauth2UserService.selectByPrimaryKey(openid);
        if(user == null){
            String s = UUID.randomUUID().toString().substring(24,35);
            User temp = new User();
            temp.setAccount(s);
            temp.setOriginFrom(clientRegistration.getRegistrationId());
            temp.setPassword("yjssaje");
            temp.setImg(userAttributes.get("figureurl_1").toString());
            String nickname = userAttributes.get("nickname").toString();
            if(nickname.length()>6){
                nickname = nickname.substring(0,6);
            }
            temp.setAlias(nickname);
            Oauth2User o2 = new Oauth2User();
            o2.setAccessToken(tokenValue);
            o2.setExpireTime(Long.parseLong(additionalParameters.get("expires_in").toString()));
            o2.setOpenId(openid);
            o2.setProviderId(clientRegistration.getRegistrationId());
            o2.setRefreshToken(additionalParameters.get("refresh_token").toString());
            o2.setRegisterTime(new Date());
            o2.setUserAccount(s);
            oauth2UserService.insert(o2,temp);
            user = oauth2UserService.selectByPrimaryKey(openid);
            Assert.notNull(user, "用户 cannot be null");
        }else{
            Oauth2User o2 = new Oauth2User();
            o2.setOpenId(openid);
            o2.setAccessToken(tokenValue);
            o2.setExpireTime(Long.parseLong(additionalParameters.get("expires_in").toString()));
            o2.setRefreshToken(additionalParameters.get("refresh_token").toString());
            oauth2UserService.updateByPrimaryKey(o2);
        }
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        List<String> ps = permissionService.selectPermissions(user.getAccount());
        for (String authority : ps){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authority));
        }
        return new LoginUser(user, authorities);
    }
}