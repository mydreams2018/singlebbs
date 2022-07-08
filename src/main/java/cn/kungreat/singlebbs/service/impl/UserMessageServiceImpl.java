package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.UserMessage;
import cn.kungreat.singlebbs.mapper.UserMessageMapper;
import cn.kungreat.singlebbs.query.UserMessageQuery;
import cn.kungreat.singlebbs.security.LoginUser;
import cn.kungreat.singlebbs.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
@Service
public class UserMessageServiceImpl implements UserMessageService {
    @Autowired
    private UserMessageMapper userMessageMapper;

    @Override
    public List<UserMessage> selectAll(UserMessageQuery query) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        query.setAlias(loginUser.getAlias());
        return userMessageMapper.selectAll(query);
    }

    @Override
    public int selectCount(UserMessageQuery query){
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        query.setAlias(loginUser.getAlias());
        return userMessageMapper.selectCount(query);
    }

    @Transactional
    public int deleteByPrimaryKey(Long id) {
        Assert.isTrue(id != null,"ID异常");
        UserMessage userMessage = userMessageMapper.selectByPrimaryKey(id);
        Assert.isTrue(userMessage != null,"数据异常");
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assert.isTrue(userMessage.getReceiveAlias().equals(loginUser.getAlias()),"没有权限删除");
        return userMessageMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public int deleteByAccount(UserMessageQuery query) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        query.setAlias(loginUser.getAlias());
        return userMessageMapper.deleteByAccount(query);
    }

    @Transactional
    public int deleteByAll(UserMessageQuery query){
        return userMessageMapper.deleteByAll(query);
    }

    @Transactional
    public void insertBaych(UserMessage userMessage) {
        userMessageMapper.insertBaych(userMessage);
    }
}
