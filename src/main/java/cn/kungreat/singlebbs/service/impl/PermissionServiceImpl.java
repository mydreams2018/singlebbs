package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.Permission;
import cn.kungreat.singlebbs.mapper.PermissionMapper;
import cn.kungreat.singlebbs.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> selectPermissions(String account) {
        return permissionMapper.selectPermissions(account);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }
}
