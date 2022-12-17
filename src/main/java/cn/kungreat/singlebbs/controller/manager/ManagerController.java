package cn.kungreat.singlebbs.controller.manager;

import cn.kungreat.singlebbs.domain.DetailsText;
import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.query.DetailsTextQuery;
import cn.kungreat.singlebbs.query.ReportQuery;
import cn.kungreat.singlebbs.query.UserQuery;
import cn.kungreat.singlebbs.service.ManagerService;
import cn.kungreat.singlebbs.service.UserService;
import cn.kungreat.singlebbs.vo.JsonResult;
import cn.kungreat.singlebbs.vo.QueryResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    private UserService userService;
    @Autowired
    private ManagerService managerService;
    @Value("${user.imgPath}")
    private String path;
    @Value("#{'${user.manager}'.split(',')}")
    private List<String> manager;

    @RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
    public QueryResult getAllUser(UserQuery userQuery) {
        userQuery.setClassId(1);//返回json格式代时需要
        return userService.getAllUser(userQuery);
    }
    @RequestMapping(value = "/getAllPorts", method = RequestMethod.POST)
    public List<Report> getAllPorts(ReportQuery reportQuery) {
        return managerService.getAllPorts(reportQuery);
    }
    @RequestMapping(value = "/getAllPortsReply", method = RequestMethod.POST)
    public List<DetailsText> getAllPortsReply(DetailsTextQuery detailsTextQuery) {
        return managerService.getAllPortsReply(detailsTextQuery);
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
    @RequestMapping(value = "/updateReplyPortAuth", method = RequestMethod.POST)
    public JsonResult updateReplyPortAuth(DetailsText detailsText) {
        JsonResult jsonResult = new JsonResult();
        try{
            managerService.updateReplyPortAuth(detailsText);
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
            managerService.deleteUser(userQuery);
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
    @RequestMapping(value = "/uploadImg")
    public JsonResult uploadImg(MultipartFile file){
        JsonResult jsonResult = new JsonResult();
        try{
            String account = SecurityContextHolder.getContext().getAuthentication().getName();
            Assert.isTrue(manager.contains(account),"没有权限操作此接口");
            Assert.isTrue("image/jpeg".equals(file.getContentType())
                    ||"image/gif".equals(file.getContentType())
                    || "image/jpg".equals(file.getContentType()),"只支持jpg或gif格式的图片");
            String type = file.getContentType().split("/")[1];
            String name = RandomStringUtils.randomAlphabetic(8);
            String rans = name +"."+type;
            String img = path +"userImg/collaboration/"+rans;
            file.transferTo(new File(img));
            String path = "/api/userImg/collaboration/"+rans;
            jsonResult.setAction(path);
        }catch (Exception e){
            jsonResult.setResult(false);
            jsonResult.setStatus(0);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

}
