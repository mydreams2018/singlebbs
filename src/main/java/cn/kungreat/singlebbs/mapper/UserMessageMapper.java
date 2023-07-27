package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.UserMessage;
import cn.kungreat.singlebbs.query.UserMessageQuery;

import java.util.List;

public interface UserMessageMapper {
    int insert(UserMessage record);

    List<UserMessage> selectAll(UserMessageQuery userMessageQuery);

    int updateViewMessage(UserMessage record);

    int updateAuthFlag(UserMessage record);

    UserMessage selectByPrimaryKey(Long id);
}