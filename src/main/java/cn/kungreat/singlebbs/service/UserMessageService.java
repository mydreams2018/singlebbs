package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.DetailsText;
import cn.kungreat.singlebbs.domain.UserMessage;
import cn.kungreat.singlebbs.query.UserMessageQuery;

import java.util.List;

public interface UserMessageService {
    List<UserMessage> selectAll(UserMessageQuery userMessageQuery);

    DetailsText selectDetailsText(UserMessageQuery userMessageQuery);
}
