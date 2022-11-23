package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.AuthLog;
import java.util.List;

public interface AuthLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthLog record);

    AuthLog selectByPrimaryKey(Long id);

    List<AuthLog> selectAll();

    int updateByPrimaryKey(AuthLog record);
}