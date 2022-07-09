package cn.kungreat.singlebbs.config;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public enum MyClientRegistrations {
    QQ(ClientRegistration.withRegistrationId("qq")
            .clientId("101923653")
            .clientSecret("022948b5bff8d9c58f4186e5d780e56c")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("https://www.kungreat.cn/api/auth/qq")
            .scope("get_user_info")
            .authorizationUri("https://graph.qq.com/oauth2.0/authorize")
            .tokenUri("https://graph.qq.com/oauth2.0/token")
            .userInfoUri("https://graph.qq.com/user/get_user_info")
            .userNameAttributeName("nickname")
//                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
            .clientName("QQ")
            .build());
    private ClientRegistration clientRegistration;
    MyClientRegistrations(ClientRegistration clientRegistration){
        this.clientRegistration = clientRegistration;
    }

    public ClientRegistration getClientRegistration() {
        return clientRegistration;
    }
}
