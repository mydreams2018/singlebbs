package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.UserMessage;
import cn.kungreat.singlebbs.query.UserMessageQuery;

import java.util.List;

public interface UserMessageMapper {
    int deleteByPrimaryKey(Long id);
    int deleteByAccount(UserMessageQuery query);
    int deleteByAll(UserMessageQuery query);

    List<UserMessage> selectAll(UserMessageQuery query);
    int selectCount(UserMessageQuery query);
    UserMessage selectByPrimaryKey(Long id);

    void insertBaych(UserMessage userMessage);
}