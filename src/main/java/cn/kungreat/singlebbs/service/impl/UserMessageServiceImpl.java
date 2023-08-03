package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.DetailsText;
import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.domain.UserMessage;
import cn.kungreat.singlebbs.mapper.DetailsTextMapper;
import cn.kungreat.singlebbs.mapper.ReportMapper;
import cn.kungreat.singlebbs.mapper.UserMessageMapper;
import cn.kungreat.singlebbs.query.DetailsTextQuery;
import cn.kungreat.singlebbs.query.UserMessageQuery;
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
    @Autowired
    private DetailsTextMapper detailsTextMapper;
    @Autowired
    private ReportMapper reportMapper;
    @Override
    public List<UserMessage> selectAll(UserMessageQuery userMessageQuery) {
        userMessageQuery.setAuthFlag(1);
        userMessageQuery.setUserAccount(SecurityContextHolder.getContext().getAuthentication().getName());
        return userMessageMapper.selectAll(userMessageQuery);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMsgState(UserMessageQuery userMessageQuery) {
        Assert.isTrue(userMessageQuery.getId() != null,"必要入参为空");
        UserMessage userMessage = userMessageMapper.selectByPrimaryKey(userMessageQuery.getId());
        Assert.isTrue(userMessage != null && userMessage.getAuthFlag().equals(1) && userMessage.getMsgState().equals(0) &&
                userMessage.getUserAccount().equals(SecurityContextHolder.getContext().getAuthentication().getName())
                ,"数据权限有问题");
        if(!userMessage.getMsgType().equals(1)){
            DetailsTextQuery detailsTextQuery = new DetailsTextQuery();
            detailsTextQuery.setId(userMessage.getDetailsId());
            detailsTextQuery.setClassId(userMessage.getClassId());
            detailsTextQuery.setPortIsauth(1);//必须已经审核通过的
            DetailsText detailsText = detailsTextMapper.selectByPrimaryKey(detailsTextQuery);
            Assert.isTrue(detailsText != null,"数据可能正在修改审核中...");
        }
        userMessage.setMsgState(1);
        userMessageMapper.updateViewMessage(userMessage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUserMessage(DetailsText record) {
        UserMessage userMessage = new UserMessage();
        userMessage.setPortId(record.getPortId());
        userMessage.setClassId(record.getClassId());
        userMessage.setDetailsId(record.getId());
        userMessage.setAuthFlag(record.getAuthFlag());
        userMessage.setUserAlias(record.getAlias());
        if(record.getReplyParent() != null){
            userMessage.setMsgType(3);
            DetailsTextQuery detailsTextQuery = new DetailsTextQuery();
            detailsTextQuery.setClassId(record.getClassId());
            detailsTextQuery.setId(record.getReplyParent());
            DetailsText detailsText = detailsTextMapper.selectByPrimaryKeyUpdate(detailsTextQuery);
            Assert.isTrue(detailsText != null,"数据异常...");
            userMessage.setUserAccount(detailsText.getUserAccount());
        }else{
            userMessage.setMsgType(2);
            Report report = new Report();
            report.setClassId(record.getClassId());
            report.setId(record.getPortId());
            Report selectById = reportMapper.selectById(report);
            Assert.isTrue(selectById != null,"数据异常...");
            userMessage.setUserAccount(selectById.getUserAccount());
        }
        return userMessageMapper.insert(userMessage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAuthFlag(DetailsText record) {
        if(record.getAuthFlag() != 1){
            return 0;
        }
        UserMessage userMessage = new UserMessage();
        userMessage.setClassId(record.getClassId());
        userMessage.setDetailsId(record.getId());
        userMessage.setAuthFlag(record.getAuthFlag());
        return userMessageMapper.updateAuthFlag(userMessage);
    }
}
