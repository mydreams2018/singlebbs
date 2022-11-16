package cn.kungreat.singlebbs.controller.manager;

import cn.kungreat.singlebbs.mapper.ManagerMapper;
import cn.kungreat.singlebbs.query.UserQuery;
import cn.kungreat.singlebbs.security.LoginUser;
import cn.kungreat.singlebbs.service.UserService;
import cn.kungreat.singlebbs.vo.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    private UserService userService;
    @Autowired
    private ManagerMapper managerMapper;

    @RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
    public QueryResult getAllUser(UserQuery userQuery) {
        userQuery.setClassId(1);//返回json格式代时需要
        return userService.getAllUser(userQuery);
    }

    @RequestMapping(value = "/selectAuthCount", method = RequestMethod.POST)
    public Map<String, Integer> selectAuthCount() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Byte isManager = loginUser.getUser().getIsManager();
        Assert.isTrue(isManager == 1, "不是管理员,没权审核");
        Map<String, Integer> map = new HashMap<>();
        map.put("port", managerMapper.selectAuthPorts());
        map.put("answerPort", managerMapper.selectAuthAnswerPorts());
        return map;
    }
}
