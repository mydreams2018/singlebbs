package cn.kungreat.singlebbs.controller;

import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.domain.User;
import cn.kungreat.singlebbs.query.UserQuery;
import cn.kungreat.singlebbs.service.DetailsTextService;
import cn.kungreat.singlebbs.service.ReportService;
import cn.kungreat.singlebbs.service.UserService;
import cn.kungreat.singlebbs.vo.JsonResult;
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

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private DetailsTextService detailsTextService;
    @Value("${user.imgPath}")
    private String path;

    @RequestMapping(value = "/home",method = RequestMethod.POST)
    public User home(UserQuery query){
        return userService.selectByunique(null,query.getAlias());
    }

    @RequestMapping(value = "/lastSendPort",method = RequestMethod.POST)
    public List<Report> lastSendPort(Report query){
        return reportService.lastSendPort(query);
    }

    @RequestMapping(value = "/lastReplyPort",method = RequestMethod.POST)
    public List<Report> lastReplyPort(Report query){
        return detailsTextService.lastReplyPort(query);
    }

    @RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.POST)
    public JsonResult updateByPrimaryKey(User user){
        JsonResult jsonResult = new JsonResult();
        try{
            userService.updateByPrimaryKey(user);
        }catch(Exception e){
            jsonResult.setResult(false);
            jsonResult.setStatus(0);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "/uploadImg")
    public JsonResult uploadImg(MultipartFile file){
        JsonResult jsonResult = new JsonResult();
        try{
//            spring.servlet.multipart.max-file-size=1MB  默认的文件上传大小是1m
//            Assert.isTrue(imgPath.getSize() < 1048576,"上传文件不能大于1M");
            Assert.isTrue("image/jpeg".equals(file.getContentType())
                    ||"image/gif".equals(file.getContentType())
                    || "image/jpg".equals(file.getContentType())
                    || "image/png".equals(file.getContentType()) ,"只支持jpg,png,gif格式的图片");
            String type = file.getContentType().split("/")[1];
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            String rans = name +"."+type;
            String img = path +"userImg/user/"+rans;
            file.transferTo(new File(img));
            String path = "/api/userImg/user/"+rans;
            jsonResult.setAction(path);
            userService.updateImg(name,path);
        }catch (Exception e){
            jsonResult.setResult(false);
            jsonResult.setStatus(0);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "/rePass",method = RequestMethod.POST)
    public JsonResult rePass(User user){
        JsonResult jsonResult = new JsonResult();
        try{
            userService.rePass(user);
            jsonResult.setMsg("设置成功,下次请使用新密码");
        }catch (Exception e){
            jsonResult.setResult(false);
            jsonResult.setStatus(0);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    public JsonResult resetPassword(User user){
        JsonResult jsonResult = new JsonResult();
        try{
            userService.resetPassword(user);
            jsonResult.setAction("/user/login.html");
        }catch (Exception e){
            jsonResult.setResult(false);
            jsonResult.setId("imgCode");
            jsonResult.setStatus(0);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }
}