package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<String> selectPermissions(String account);
    List<Permission> selectAll();
}
