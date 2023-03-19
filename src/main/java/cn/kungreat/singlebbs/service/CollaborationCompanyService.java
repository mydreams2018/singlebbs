package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.CollaborationCompany;
import cn.kungreat.singlebbs.query.CollaborationCompanyQuery;
import cn.kungreat.singlebbs.vo.QueryResult;

public interface CollaborationCompanyService {
    int insert(CollaborationCompany record);

    QueryResult selectAll(CollaborationCompanyQuery query);
}
