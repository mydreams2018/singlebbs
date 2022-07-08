package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.PermissionMapping;

import java.util.List;

public interface PermissionMappingService {
    int insertBatch(List<String> record,String account);

    List<PermissionMapping> selectAll(String account);
}
