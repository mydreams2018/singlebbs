package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    List<Permission> selectAll();

    List<String> selectPermissions(String account);
    int insertBatch(@Param("ps") List<Permission> record);
    int deleteRepeat();
}