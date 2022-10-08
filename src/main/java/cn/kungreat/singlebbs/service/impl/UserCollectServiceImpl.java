package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.UserCollect;
import cn.kungreat.singlebbs.mapper.UserCollectMapper;
import cn.kungreat.singlebbs.query.UserCollectQuery;
import cn.kungreat.singlebbs.service.UserCollectService;
import cn.kungreat.singlebbs.vo.JsonResult;
import cn.kungreat.singlebbs.vo.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UserCollectServiceImpl implements UserCollectService {
    @Autowired
    private UserCollectMapper userCollectMapper;

    @Override
    public UserCollect selectByPrimaryKey(UserCollect collect) {
        collect.setUserAccount(SecurityContextHolder.getContext().getAuthentication().getName());
        return userCollectMapper.selectByPrimaryKey(collect);
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Long id) {
        return userCollectMapper.deleteByPrimaryKey(id,SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Transactional(rollbackFor = Exception.class)
    public UserCollect sendCollect(UserCollect collect){
        if(collect.getId() == null){
            Assert.isTrue(collect.getClassId() != null && collect.getPortId() != null,"ID异常");
            Assert.isTrue(collect.getPortTitle()!=null && collect.getPortTitle().length()>0,"贴子名称异常");
            collect.setUserAccount(SecurityContextHolder.getContext().getAuthentication().getName());
            collect.setCollectTime(new Date());
            userCollectMapper.insert(collect);
            return collect;
        }
        deleteByPrimaryKey(collect.getId());
        return null;
    }

    @Override
    public QueryResult queryReport(UserCollectQuery query){
        query.setUserAccount(SecurityContextHolder.getContext().getAuthentication().getName());
        Integer count = userCollectMapper.selectCount(query);
        List list  = Collections.emptyList();
        if (count >  0){
            list = userCollectMapper.selectAll(query);
        }
        query.setData(count,query.getPageSize(),query.getCurrentPage());
        QueryResult  result = new QueryResult();
        result.setDatas(list);
        result.setPage(query);
        return result;
    }

    @Override
    public int deleteReports(UserCollectQuery query) {
        Assert.isTrue(StringUtils.isNotBlank(query.getIds()),"删除数据ID不能为空");
        query.setUserAccount(SecurityContextHolder.getContext().getAuthentication().getName());
        return userCollectMapper.deleteReports(query);
    }
}