package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.DetailsText;
import cn.kungreat.singlebbs.domain.UserMessage;
import cn.kungreat.singlebbs.mapper.DetailsTextMapper;
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
}
