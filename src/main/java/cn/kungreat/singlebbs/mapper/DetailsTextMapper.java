package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.DetailsText;
import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.query.DetailsTextQuery;
import cn.kungreat.singlebbs.query.ReportQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DetailsTextMapper {
    int deleteByPrimaryKey(DetailsTextQuery query);

    long insert(DetailsText record);

    DetailsText selectByPrimaryKey(DetailsTextQuery query);
    DetailsText selectByPort(DetailsText record);

    Integer selectCount(DetailsTextQuery query);
    List<DetailsText> selectAll(DetailsTextQuery query);
    List<DetailsText> selectChildAnswer(DetailsTextQuery query);
    int updateByPrimaryKey(DetailsTextQuery query);

    DetailsText selectLikeAccount(DetailsTextQuery query);

    int updateLikeAccount(DetailsTextQuery query);
    int updateAdoption(DetailsTextQuery query);

    int updateByPortId(DetailsText detailsText);

    List<Report> lastReplyPort(Report query);

    Integer myReplyPortsCount(ReportQuery query);

    List<DetailsText> myReplyPortsAll(ReportQuery query);

    int deleteByPrimaryKeys(@Param("id") String id, @Param("account")String account
            , @Param("tableName")String tableName);
}