package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.CollaborationCompany;
import cn.kungreat.singlebbs.query.CollaborationCompanyQuery;

import java.util.List;

public interface CollaborationCompanyMapper {

    int insert(CollaborationCompany record);

    List<CollaborationCompany> selectAll(CollaborationCompanyQuery record);
    int selectCount(CollaborationCompanyQuery record);

    int updateByPrimaryKey(CollaborationCompany record);

    int updateCollaborationCompany();
    int updateCollaborationCompanyActive();
}