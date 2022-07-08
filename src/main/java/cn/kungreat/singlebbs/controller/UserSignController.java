package cn.kungreat.singlebbs.controller;

import cn.kungreat.singlebbs.domain.UserSign;
import cn.kungreat.singlebbs.service.UserSignService;
import cn.kungreat.singlebbs.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userSign")
public class UserSignController {
    @Autowired
    private UserSignService userSignService;

    @RequestMapping(value = "/selectByPrimaryKey",method = RequestMethod.POST)
    public UserSign selectByPrimaryKey() {
        return userSignService.selectByPrimaryKey();
    }

    @RequestMapping(value = "/signOn",method = RequestMethod.POST)
    public JsonResult signOn(){
        JsonResult jsonResult = new JsonResult();
        try{
            userSignService.signOn();
            jsonResult.setMsg("success");
        }catch(Exception e){
            jsonResult.setResult(false);
            jsonResult.setStatus(0);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }
}