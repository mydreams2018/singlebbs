package cn.kungreat.singlebbs.controller;

import cn.kungreat.singlebbs.domain.UserMessage;
import cn.kungreat.singlebbs.query.UserMessageQuery;
import cn.kungreat.singlebbs.service.UserMessageService;
import cn.kungreat.singlebbs.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/userMessage")
public class UserMessageController {
    @Autowired
    private UserMessageService userMessageService;

    @RequestMapping(value = "/selectAll",method = RequestMethod.POST)
    public List<UserMessage> selectAll(UserMessageQuery userMessageQuery){
        return userMessageService.selectAll(userMessageQuery);
    }

    @RequestMapping(value = "/updateMsgState",method = RequestMethod.POST)
    public JsonResult updateMsgState(UserMessageQuery userMessageQuery){
        JsonResult jsonResult = new JsonResult();
        try {
            userMessageService.updateMsgState(userMessageQuery);
        }catch(Exception e){
            jsonResult.setResult(false);
            jsonResult.setStatus(0);
            jsonResult.setId("imgCode");
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

}
