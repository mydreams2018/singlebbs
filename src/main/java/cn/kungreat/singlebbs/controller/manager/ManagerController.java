package cn.kungreat.singlebbs.controller.manager;

import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.query.ReportQuery;
import cn.kungreat.singlebbs.query.UserQuery;
import cn.kungreat.singlebbs.service.ManagerService;
import cn.kungreat.singlebbs.service.UserService;
import cn.kungreat.singlebbs.vo.JsonResult;
import cn.kungreat.singlebbs.vo.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    private UserService userService;
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
    public QueryResult getAllUser(UserQuery userQuery) {
        userQuery.setClassId(1);//返回json格式代时需要
        return userService.getAllUser(userQuery);
    }
    @RequestMapping(value = "/getAllPorts", method = RequestMethod.POST)
    public List<Report> getAllPorts(ReportQuery reportQuery) {
        return managerService.getAllPorts(reportQuery);
    }
    @RequestMapping(value = "/updatePortAuth", method = RequestMethod.POST)
    public JsonResult updatePortAuth(Report reportQuery) {
        JsonResult jsonResult = new JsonResult();
        try{
            managerService.updatePortAuth(reportQuery);
            jsonResult.setMsg("success");
        }catch(Exception e){
            jsonResult.setResult(false);
            jsonResult.setStatus(0);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.POST)
    public Report selectByPrimaryKey(Report record){
        return managerService.selectByPrimaryKey(record);
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public JsonResult deleteUser(UserQuery userQuery) {
        JsonResult jsonResult = new JsonResult();
        try{
            userService.deleteUser(userQuery);
            jsonResult.setMsg("success");
        }catch(Exception e){
            jsonResult.setResult(false);
            jsonResult.setStatus(0);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "/selectAuthCount", method = RequestMethod.POST)
    public Map<String, Integer> selectAuthCount() {
        return managerService.selectAuthCount();
    }
}
