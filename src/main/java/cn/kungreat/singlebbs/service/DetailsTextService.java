package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.DetailsText;
import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.query.DetailsTextQuery;
import cn.kungreat.singlebbs.query.UserQuery;
import cn.kungreat.singlebbs.vo.QueryResult;

import java.util.List;

public interface DetailsTextService {
    QueryResult queryReport(DetailsTextQuery query);
    long insert(DetailsText record);

    void likeAccount(DetailsTextQuery query);

    int deleteReplyPort(DetailsTextQuery query);

    void acceptReply(DetailsTextQuery query);

    DetailsText selectByPrimaryKey(DetailsTextQuery query);

    void updateByPrimaryKey(DetailsTextQuery query);

    List<Report> lastReplyPort(Report query);

    Integer deleteByPrimaryKeys(UserQuery record);
}