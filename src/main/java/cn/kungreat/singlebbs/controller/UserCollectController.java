package cn.kungreat.singlebbs.controller;

import cn.kungreat.singlebbs.domain.UserCollect;
import cn.kungreat.singlebbs.query.UserCollectQuery;
import cn.kungreat.singlebbs.service.UserCollectService;
import cn.kungreat.singlebbs.vo.JsonResult;
import cn.kungreat.singlebbs.vo.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userCollect")
public class UserCollectController{
    @Autowired
    private UserCollectService userCollectService;

    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.POST)
    public UserCollect selectByPrimaryKey(UserCollect collect){
        return userCollectService.selectByPrimaryKey(collect);
    }

    @RequestMapping(value = "/sendCollect",method = RequestMethod.POST)
    public UserCollect sendCollect(UserCollect collect){
        return userCollectService.sendCollect(collect);
    }

    @RequestMapping(value = "/queryReport",method = RequestMethod.POST)
    public QueryResult queryReport(UserCollectQuery query){
        return userCollectService.queryReport(query);
    }

    @RequestMapping(value = "/deleteReports",method = RequestMethod.POST)
    public JsonResult deleteReports(UserCollectQuery query){
        JsonResult jsonResult = new JsonResult();
        try{
            userCollectService.deleteReports(query);
            jsonResult.setMsg("success");
        }catch(Exception e){
            jsonResult.setResult(false);
            jsonResult.setStatus(0);
            jsonResult.setId("imgCode");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }
}