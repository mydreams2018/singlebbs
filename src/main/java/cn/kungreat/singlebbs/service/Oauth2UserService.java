package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.Oauth2User;
import cn.kungreat.singlebbs.domain.User;

public interface Oauth2UserService {
    int insert(Oauth2User record,User user);

    User selectByPrimaryKey(String openId);

    int updateByPrimaryKey(Oauth2User record);
}
