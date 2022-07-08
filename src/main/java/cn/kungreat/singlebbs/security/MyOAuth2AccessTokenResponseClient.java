package cn.kungreat.singlebbs.security;

import cn.kungreat.singlebbs.util.UserAccumulate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.util.Assert;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

public class MyOAuth2AccessTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {
    private RestOperations restOperations;
    {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        this.restOperations = restTemplate;
    }
    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
        Assert.notNull(authorizationGrantRequest, "authorizationCodeGrantRequest cannot be null");
        OAuth2AuthorizationExchange authorizationExchange = authorizationGrantRequest.getAuthorizationExchange();
        ClientRegistration clientRegistration = authorizationGrantRequest.getClientRegistration();
        String getByUrl = clientRegistration.getProviderDetails().getTokenUri()+"?grant_type=authorization_code&client_id="+clientRegistration.getClientId()
                +"&client_secret="+clientRegistration.getClientSecret()+"&code="+authorizationExchange.getAuthorizationResponse().getCode()+"&redirect_uri="+clientRegistration.getRedirectUriTemplate();
        ResponseEntity<String> response;
        try {
            response = this.restOperations.getForEntity(getByUrl,String.class);
        }catch(Exception ex) {
            OAuth2Error oauth2Error = new OAuth2Error("invalid_token_response",
                    "An error occurred while attempting to retrieve the OAuth 2.0 Access Token Response: " + ex.getMessage(), null);
            throw new OAuth2AuthorizationException(oauth2Error, ex);
        }
        Map<String,Object> stringObjectMap = Collections.emptyMap();
        if("qq".equals(clientRegistration.getRegistrationId())){
            stringObjectMap = UserAccumulate.getMapByString(response.getBody());
        }
        OAuth2AccessTokenResponse.Builder builder = OAuth2AccessTokenResponse.withToken(stringObjectMap.get("access_token").toString());
        builder.tokenType(OAuth2AccessToken.TokenType.BEARER);
        builder.scopes(clientRegistration.getScopes());
        builder.expiresIn(Long.parseLong(stringObjectMap.get("expires_in").toString()));
        builder.refreshToken(stringObjectMap.get("refresh_token").toString());
        builder.additionalParameters(stringObjectMap);
        return builder.build();
    }
}
