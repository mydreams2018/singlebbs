package cn.kungreat.singlebbs.controller;

import cn.kungreat.singlebbs.query.CollaborationCompanyQuery;
import cn.kungreat.singlebbs.service.CollaborationCompanyService;
import cn.kungreat.singlebbs.vo.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/collaborationCompany")
public class CollaborationCompanyController {
    @Autowired
    private CollaborationCompanyService collaborationCompanyService;

    @RequestMapping(value = "/selectAll",method = RequestMethod.POST)
    public QueryResult selectAll(CollaborationCompanyQuery query){
        return collaborationCompanyService.selectAll(query);
    }

}
