package cn.kungreat.singlebbs.controller;

import cn.kungreat.singlebbs.domain.UserReplyPort;
import cn.kungreat.singlebbs.service.UserReplyPortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/userReplyPort")
public class UserReplyPortController {
    @Autowired
    private UserReplyPortService userReplyPortService;

    @RequestMapping(value = "/selectAll",method = RequestMethod.POST)
    public List<UserReplyPort> selectAll(){
        return userReplyPortService.selectAll();
    }
}
