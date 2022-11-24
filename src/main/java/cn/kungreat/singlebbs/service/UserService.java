package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.User;
import cn.kungreat.singlebbs.query.UserQuery;
import cn.kungreat.singlebbs.vo.QueryResult;


public interface UserService {

    int insert(User record);

    User selectByPrimaryKey(String account);
    int updateByPrimaryKey(User record);
    int updateImg(String account,String path);
    int updateAccumulatePoints(int number,String account);

    User selectByunique(String account, String alias);

    void rePass(User user);

    void resetPassword(User user);

    QueryResult getAllUser(UserQuery userQuery);

}