package cn.kungreat.singlebbs.controller;

import cn.kungreat.singlebbs.domain.User;
import cn.kungreat.singlebbs.service.UserService;
import cn.kungreat.singlebbs.vo.JsonResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Collections;
import java.util.Map;

@RestController
public class BaseController {
    @Autowired
    private UserService userService;
    @Value("${user.imgPath}")
    private String path;

    @RequestMapping(value = "/index")
    public Map index(){
        return Collections.emptyMap();
    }

    @RequestMapping(value = "/getCurrentUser",method = RequestMethod.GET)
    public User getCurrentUser(){
        return userService.selectByPrimaryKey(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @RequestMapping(value = "/image")
    public String image(HttpServletRequest request){
        int randomStr = RandomUtils.nextInt(1,10);
        int randomMid = RandomUtils.nextInt(1,10);
        int randomend = RandomUtils.nextInt(1,10);
        int syb1 = RandomUtils.nextInt(1,5);
        String rt;
        switch (syb1){
            case 1:
                rt = randomStr+"+"+randomMid+"*"+randomend;
                break;
            case 2:
                rt = randomStr+"+"+randomMid+"-"+randomend;
                break;
            case 3:
                rt = randomStr+"*"+randomMid+"-"+randomend;
                break;
            case 4:
                rt = randomStr+"-"+randomMid+"*"+randomend;
                break;
            default:
                rt = randomStr+"*"+randomMid+"+"+randomend;
        }
        request.getSession().setAttribute("image_code", rt);
        request.getSession().setAttribute("time", System.currentTimeMillis());
        return rt;
    }

    @RequestMapping(value = "/register")
    public JsonResult register(User record){
        JsonResult jsonResult = new JsonResult();
        try{
            userService.insert(record);
            jsonResult.setMsg("success");
            jsonResult.setAction("/user/login.html");
        }catch(Exception e){
            jsonResult.setResult(false);
            jsonResult.setStatus(0);
            jsonResult.setId("imgCode");
            jsonResult.setMsg(e.getMessage());
            jsonResult.setAction("/user/reg.html");
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
                    || "image/jpg".equals(file.getContentType()),"只支持jpg或gif格式的图片");
            String type = file.getContentType().split("/")[1];
            String rans = RandomStringUtils.randomAlphabetic(8)+"."+type;
            String img = path +"userImg/"+rans;
            file.transferTo(new File(img));
            String path = "/api/userImg/"+rans;
            jsonResult.setAction(path);
        }catch (Exception e){
            jsonResult.setResult(false);
            jsonResult.setStatus(0);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }
}