package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.UserCollect;
import cn.kungreat.singlebbs.query.UserCollectQuery;
import cn.kungreat.singlebbs.vo.JsonResult;
import cn.kungreat.singlebbs.vo.QueryResult;

public interface UserCollectService {
    UserCollect selectByPrimaryKey(UserCollect collect);
    int deleteByPrimaryKey(Long id);
    UserCollect sendCollect(UserCollect collect);
    QueryResult queryReport(UserCollectQuery query);

    int deleteReports(UserCollectQuery query);
}