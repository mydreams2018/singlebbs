package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.PermissionMapping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMappingMapper {

    int insertBatch(@Param("ps") List<String> record,@Param("account")String account);
    int deleteByAccount(String account);
    List<PermissionMapping> selectAll(String account);

}