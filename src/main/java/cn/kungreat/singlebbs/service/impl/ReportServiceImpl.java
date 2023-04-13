package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.DetailsText;
import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.domain.User;
import cn.kungreat.singlebbs.mapper.DetailsTextMapper;
import cn.kungreat.singlebbs.mapper.ReportMapper;
import cn.kungreat.singlebbs.query.ReportQuery;
import cn.kungreat.singlebbs.query.UserQuery;
import cn.kungreat.singlebbs.security.LoginUser;
import cn.kungreat.singlebbs.service.ReportService;
import cn.kungreat.singlebbs.service.UserService;
import cn.kungreat.singlebbs.vo.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private UserService userService;
    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private DetailsTextMapper detailsTextMapper;
    @Value("${portIsauth}")
    private Integer portIsauth;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKeys(UserQuery userQuery) {
        Assert.isTrue(StringUtils.isNotEmpty(userQuery.getIds()),"删除数据ID不能为空");
        userQuery.setAccount(SecurityContextHolder.getContext().getAuthentication().getName());
        String[] split = userQuery.getIds().split(",");
        for (int i = 0; i < split.length; i++) {
            reportMapper.deleteByPrimaryKey(split[i].split("-")[0]
                    ,userQuery.getAccount(),split[i].split("-")[1]);
        }
        return split.length;
    }

    @Transactional(rollbackFor = Exception.class)
    public long insert(Report record) {
        String s = record.validMessage();
        Assert.isTrue(StringUtils.isEmpty(s),s);
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(record.getExperience() != null && record.getExperience() > 0){
            userService.updateAccumulatePoints(-record.getExperience(),loginUser.getName());
        }
        record.setUserAccount(loginUser.getName());
        Date date = new Date();
        record.setCreateTime(date);
        if(loginUser.getUser().getIsManager() == 1){
            record.setAuthFlag(1);
        }else{
            record.setAuthFlag(portIsauth);
        }
        reportMapper.insert(record);
        DetailsText details = new DetailsText();
        details.setIsPort(true);
        details.setCreateData(date);
        details.setPortId(record.getId());
        details.setDetailsText(record.getDetailsText());
        details.setUserAccount(loginUser.getName());
        details.setClassId(record.getClassId());
        if(loginUser.getUser().getIsManager() == 1){
            details.setAuthFlag(1);
        }else{
            details.setAuthFlag(portIsauth);
        }
        detailsTextMapper.insert(details);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Report record) {
        Assert.isTrue(record.getClassId()!=null&&record.getClassId()>=1&&record.getClassId()<5,"类型ID异常");
        Assert.isTrue(record.getId() != null,"ID异常");
        Assert.isTrue(StringUtils.isNotEmpty(record.getName()),"标题不能为空");
        Assert.isTrue(StringUtils.isNotEmpty(record.getDetailsText()),"内容不能为空");
        Report port = reportMapper.selectById(record);
        Assert.isTrue(port != null,"贴子异常");
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assert.isTrue(loginUser.getName().equals(port.getUserAccount()),"没有权限操作");
        if(loginUser.getUser().getIsManager() == 1){
            record.setAuthFlag(1);
        }else{
            record.setAuthFlag(portIsauth);
        }
        long currentTimeMillis = System.currentTimeMillis();
        record.setUpdateTime(currentTimeMillis);
        reportMapper.updateByPrimaryKey(record);
        DetailsText detailsText = new DetailsText();
        detailsText.setClassId(record.getClassId());
        detailsText.setPortId(record.getId());
        detailsText.setDetailsText(record.getDetailsText());
        if(loginUser.getUser().getIsManager() == 1){
            detailsText.setAuthFlag(1);
        }else{
            detailsText.setAuthFlag(portIsauth);
        }
        detailsText.setUpdateTime(currentTimeMillis);
        detailsTextMapper.updateByPortId(detailsText);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateBystate(Report record) {
        return reportMapper.updateBystate(record);
    }

    @Override
    public Report selectByPrimaryKey(Report record) {
        Assert.isTrue(record.getClassId()!=null&&record.getClassId()>=1&&record.getClassId()<5,"类型ID异常");
        Assert.isTrue(record.getId() != null,"ID异常");
        record.setPortIsauth(1);
        Report report = reportMapper.selectByPrimaryKey(record);
        if(report != null){
            DetailsText de = new DetailsText();
            de.setPortId(record.getId());
            de.setClassId(record.getClassId());
            de.setPortIsauth(1);
            report.setDetails(detailsTextMapper.selectByPort(de));
        }
        return report;
    }

    @Override
    public QueryResult queryReport(ReportQuery query) {
        Assert.isTrue(query.getClassId()!=null&&query.getClassId()>=1&&query.getClassId()<5,"类型ID异常");
        query.setPortIsauth(1);
        Integer count = reportMapper.selectCount(query);
        List list  = Collections.emptyList();
        if (count >  0){
            list = reportMapper.selectAll(query);
        }
        query.setData(count,query.getPageSize(),query.getCurrentPage());
        QueryResult result = new QueryResult();
        result.setDatas(list);
        result.setPage(query);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public void incrementNumber(Report port) {
        reportMapper.incrementNumber(port);
    }

    @Transactional(rollbackFor = Exception.class)
    public void decrementNumber(Report port) {
        reportMapper.decrementNumber(port);
    }

    @Override
    public List<Report> lastSendPort(Report query){
        Assert.isTrue(StringUtils.isNotEmpty(query.getAlias()),"用户为空");
        User user = userService.selectByunique(null, query.getAlias());
        Assert.isTrue(user!=null,"用户为空");
        query.setUserAccount(user.getAccount());
        query.setPortIsauth(1);
        List<Report> ports = new ArrayList<>(40);
        for(int x=1;x<5;x++){
            query.setClassId(x);
            List<Report> temp = reportMapper.lastSendPort(query);
            if(temp != null &&temp.size()>0){
                ports.addAll(temp);
            }
        }
        if(ports.size() > 1){
            order(ports);
        }
        return ports;
    }
//查个人发贴-我的发贴
    @Override
    public QueryResult myQueryReport(ReportQuery query){
        Integer count = reportMapper.mySelectCount(query);
        List list  = Collections.emptyList();
        if (count >  0){
            list = reportMapper.mySelectAll(query);
        }
        query.setData(count,query.getPageSize(),query.getCurrentPage());
        QueryResult result = new QueryResult();
        result.setDatas(list);
        result.setPage(query);
        return result;
    }

    @Override
    public QueryResult myReplyPorts(ReportQuery query) {
        Integer count = detailsTextMapper.myReplyPortsCount(query);
        List<?> list  = Collections.emptyList();
        if (count >  0){
            list = detailsTextMapper.myReplyPortsAll(query);
        }
        query.setData(count,query.getPageSize(),query.getCurrentPage());
        QueryResult result = new QueryResult();
        result.setDatas(list);
        result.setPage(query);
        return result;
    }

    private void order(List<Report> reports){
        int size = reports.size();
        Report temp;
        for(int x = 0;x < size-1;x++){
            if(x==10){
               return;
            }
            for(int y=x+1;y<size;y++){
                if(reports.get(x).getCreateTime().before(reports.get(y).getCreateTime())){
                    temp = reports.get(x);
                    reports.set(x,reports.get(y));
                    reports.set(y,temp);
                }
            }
        }
    }
}