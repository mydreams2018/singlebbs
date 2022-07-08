package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.UserReplyPort;

import java.util.List;

public interface UserReplyPortService {

    List<UserReplyPort> selectAll();

    int updateByPrimaryKey();
}
