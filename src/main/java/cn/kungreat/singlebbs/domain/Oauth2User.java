package cn.kungreat.singlebbs.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class Oauth2User {
    private String openId;

    private String userAccount;

    private String providerId;

    private String accessToken;

    private String refreshToken;

    private Long expireTime;

    private Date registerTime;
}