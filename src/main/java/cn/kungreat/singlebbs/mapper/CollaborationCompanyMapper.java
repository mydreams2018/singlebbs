package cn.kungreat.singlebbs.mapper;

import cn.kungreat.singlebbs.domain.CollaborationCompany;
import java.util.List;

public interface CollaborationCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CollaborationCompany record);

    CollaborationCompany selectByPrimaryKey(Integer id);

    List<CollaborationCompany> selectAll();

    int updateByPrimaryKey(CollaborationCompany record);
}