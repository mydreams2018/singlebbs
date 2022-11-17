package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.User;
import cn.kungreat.singlebbs.query.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int insert(User record);

    User selectByPrimaryKey(String account);

    List<User> selectAll(UserQuery query);
    int selectCount(UserQuery query);

    List<User> selectByAccounts(@Param("acts") List<String> acts);
    int updateByPrimaryKey(User record);

    int updateAccumulatePoints(@Param("current") int current, @Param("original") int original,
                               @Param("account") String account,@Param("vipLevel") int vipLevel);

    int updateImg(@Param("account") String account, @Param("path") String path);

    User selectByunique(@Param("account") String account, @Param("alias") String alias);

    int repass(@Param("account") String account, @Param("repass") String repass);

    int deleteUser(UserQuery userQuery);
}