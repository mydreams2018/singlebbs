package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.query.ReportQuery;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    List<Report> getAllPorts(ReportQuery reportQuery);

    Map<String, Integer> selectAuthCount();

    Report selectByPrimaryKey(Report record);

    void updatePortAuth(Report reportQuery);
}
