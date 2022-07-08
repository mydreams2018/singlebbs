package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.UserSign;

public interface UserSignMapper {
    int insert(UserSign record);

    UserSign selectByPrimaryKey(String account);

    int updateByPrimaryKey(UserSign record);
}