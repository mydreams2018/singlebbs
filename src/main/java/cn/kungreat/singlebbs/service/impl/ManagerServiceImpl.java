package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.mapper.ManagerMapper;
import cn.kungreat.singlebbs.query.ReportQuery;
import cn.kungreat.singlebbs.security.LoginUser;
import cn.kungreat.singlebbs.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Report> getAllPorts(ReportQuery reportQuery) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Byte isManager = loginUser.getUser().getIsManager();
        Assert.isTrue(isManager == 1, "不是管理员,没权审核");
        return managerMapper.getAllPorts(reportQuery);
    }


    @Override
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
