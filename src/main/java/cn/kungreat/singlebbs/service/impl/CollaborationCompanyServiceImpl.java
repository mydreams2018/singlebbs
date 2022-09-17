package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.CollaborationCompany;
import cn.kungreat.singlebbs.mapper.CollaborationCompanyMapper;
import cn.kungreat.singlebbs.query.CollaborationCompanyQuery;
import cn.kungreat.singlebbs.service.CollaborationCompanyService;
import cn.kungreat.singlebbs.vo.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class CollaborationCompanyServiceImpl implements CollaborationCompanyService {
    @Autowired
    private CollaborationCompanyMapper collaborationCompanyMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return collaborationCompanyMapper.deleteByPrimaryKey(id);
    }


    @Override
    public int insert(CollaborationCompany record) {
        return collaborationCompanyMapper.insert(record);
    }


    @Override
    public CollaborationCompany selectByPrimaryKey(Integer id) {
        return collaborationCompanyMapper.selectByPrimaryKey(id);
    }


    @Override
    public QueryResult selectAll(CollaborationCompanyQuery query) {
        int count = collaborationCompanyMapper.selectCount(query);
        List<?> list  = Collections.emptyList();
        if (count >  0){
            list = collaborationCompanyMapper.selectAll(query);
        }
        query.setData(count,query.getPageSize(),query.getCurrentPage());
        QueryResult result = new QueryResult();
        result.setDatas(list);
        result.setPage(query);
        return result;
    }


    @Override
    public int updateByPrimaryKey(CollaborationCompany record) {
        return collaborationCompanyMapper.updateByPrimaryKey(record);
    }
}
