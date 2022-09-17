package cn.kungreat.singlebbs.service;

import cn.kungreat.singlebbs.domain.CollaborationCompany;
import cn.kungreat.singlebbs.query.CollaborationCompanyQuery;
import cn.kungreat.singlebbs.vo.QueryResult;

public interface CollaborationCompanyService {
    int deleteByPrimaryKey(Integer id);

    int insert(CollaborationCompany record);

    CollaborationCompany selectByPrimaryKey(Integer id);

    QueryResult selectAll(CollaborationCompanyQuery query);

    int updateByPrimaryKey(CollaborationCompany record);
}
