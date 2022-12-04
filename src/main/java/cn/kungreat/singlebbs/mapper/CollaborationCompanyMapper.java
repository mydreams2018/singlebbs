package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.CollaborationCompany;
import cn.kungreat.singlebbs.query.CollaborationCompanyQuery;

import java.util.List;

public interface CollaborationCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CollaborationCompany record);

    CollaborationCompany selectByPrimaryKey(Integer id);

    List<CollaborationCompany> selectAll(CollaborationCompanyQuery record);
    int selectCount(CollaborationCompanyQuery record);

    int updateByPrimaryKey(CollaborationCompany record);

    int updateCollaborationCompany();
}