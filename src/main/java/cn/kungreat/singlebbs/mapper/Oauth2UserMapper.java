package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.Oauth2User;

public interface Oauth2UserMapper {

    int insert(Oauth2User record);

    Oauth2User selectByPrimaryKey(String openId);

    int updateByPrimaryKey(Oauth2User record);
}