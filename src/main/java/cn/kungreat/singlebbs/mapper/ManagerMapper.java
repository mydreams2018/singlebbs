package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.DetailsText;
import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.query.DetailsTextQuery;
import cn.kungreat.singlebbs.query.ReportQuery;
import cn.kungreat.singlebbs.query.UserQuery;

import java.util.List;

public interface ManagerMapper {
    /*
    * 查询需要审核的主贴数量
    */
    Integer selectAuthPorts();

    /*
     * 查询需要审核的回贴数量
     */
    Integer selectAuthAnswerPorts();
    /*
     * 查询需要审核的回贴
     */
    List<Report> getAllPorts(ReportQuery reportQuery);

    int updatePortAuth(Report record);

    void updatePortAuthDetails(DetailsTextQuery record);

    List<DetailsText> getAllPortsReply(DetailsTextQuery detailsTextQuery);

    void updateReplyPortAuth(DetailsText record);

    int deleteUserPorts(Report report);

    void deleteUserReplyPorts(DetailsText detailsText);
}
