package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.PermissionMapping;
import cn.kungreat.singlebbs.mapper.PermissionMappingMapper;
import cn.kungreat.singlebbs.service.PermissionMappingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
@Service
public class PermissionMappingServiceImpl implements PermissionMappingService {
    @Autowired
    private PermissionMappingMapper permissionMappingMapper;
    @Value("#{'${user.manager}'.split(',')}")
    private List<String> manager;

    @Transactional(rollbackFor = Exception.class)
    public int insertBatch(List<String> record,String account) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Assert.isTrue(manager.contains(name),"没有权限访问");
        Assert.isTrue(StringUtils.isNotEmpty(account),"没有用户");
        permissionMappingMapper.deleteByAccount(account);
        if(record ==null || record.size() < 1){
            return 0;
        }
        return  permissionMappingMapper.insertBatch(record,account);
    }

    @Override
    public List<PermissionMapping> selectAll(String account) {
        return permissionMappingMapper.selectAll(account);
    }
}
