package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.UserSign;

public interface UserSignService {
    int insert(UserSign record);

    UserSign selectByPrimaryKey();

    int updateByPrimaryKey(UserSign record);

    void signOn();
}
