package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.UserMessage;
import cn.kungreat.singlebbs.query.UserMessageQuery;

import java.util.List;

public interface UserMessageService {
    List<UserMessage> selectAll(UserMessageQuery query);
    int selectCount(UserMessageQuery query);
    int deleteByPrimaryKey(Long id);
    int deleteByAccount(UserMessageQuery query);
    int deleteByAll(UserMessageQuery query);
    void insertBaych(UserMessage userMessage);
}
