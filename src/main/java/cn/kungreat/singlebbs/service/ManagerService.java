package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.CollaborationCompany;
import cn.kungreat.singlebbs.domain.DetailsText;
import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.query.DetailsTextQuery;
import cn.kungreat.singlebbs.query.ReportQuery;
import cn.kungreat.singlebbs.query.UserQuery;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    List<Report> getAllPorts(ReportQuery reportQuery);

    Map<String, Integer> selectAuthCount();

    Report selectByPrimaryKey(Report record);

    void updatePortAuth(Report reportQuery);

    List<DetailsText> getAllPortsReply(DetailsTextQuery detailsTextQuery);

    void updateReplyPortAuth(DetailsText detailsText);
    int updateUserState(UserQuery userQuery);

    void collaborationInsert(CollaborationCompany collaborationCompany);

    void updatePortIsTop(Report reportQuery);
}
