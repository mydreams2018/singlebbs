package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.query.ReportQuery;
import cn.kungreat.singlebbs.query.UserQuery;
import cn.kungreat.singlebbs.vo.QueryResult;

import java.util.List;

public interface ReportService {
    int deleteByPrimaryKeys(UserQuery userQuery);
    long insert(Report record);
    int updateByPrimaryKey(Report record);
    int updateBystate(Report record);
    Report selectByPrimaryKey(Report record);

    QueryResult queryReport(ReportQuery query);

    void incrementNumber(Report port);
    void decrementNumber(Report port);

    List<Report> lastSendPort(Report query);

    QueryResult myQueryReport(ReportQuery query);
}