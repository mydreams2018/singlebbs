package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.query.ReportQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportMapper {
    int deleteByPrimaryKey(@Param("id") String id,@Param("account")String account
            ,@Param("tableName")String tableName);

    int insert(Report record);

    Report selectByPrimaryKey(Report record);
    int updateByPrimaryKey(Report record);

    Integer selectCount(ReportQuery query);
    List<Report> selectAll(ReportQuery query);

    void incrementNumber(Report port);
    void incrementLikeNumber(Report port);
    void decrementNumber(Report port);

    int updateBystate(Report record);

    Report selectById(Report port);

    List<Report> lastSendPort(Report query);

    Integer mySelectCount(ReportQuery query);

    List<Report> mySelectAll(ReportQuery query);
}