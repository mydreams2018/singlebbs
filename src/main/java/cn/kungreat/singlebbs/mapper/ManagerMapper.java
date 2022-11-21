package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.query.DetailsTextQuery;
import cn.kungreat.singlebbs.query.ReportQuery;

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
}
