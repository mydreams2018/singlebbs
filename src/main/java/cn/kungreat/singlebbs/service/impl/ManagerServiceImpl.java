package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.DetailsText;
import cn.kungreat.singlebbs.domain.Report;
import cn.kungreat.singlebbs.mapper.DetailsTextMapper;
import cn.kungreat.singlebbs.mapper.ManagerMapper;
import cn.kungreat.singlebbs.mapper.ReportMapper;
import cn.kungreat.singlebbs.query.DetailsTextQuery;
import cn.kungreat.singlebbs.query.ReportQuery;
import cn.kungreat.singlebbs.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private DetailsTextMapper detailsTextMapper;

    @Override
    public List<Report> getAllPorts(ReportQuery reportQuery) {
        return managerMapper.getAllPorts(reportQuery);
    }


    @Override
    public Map<String, Integer> selectAuthCount() {
        Map<String, Integer> map = new HashMap<>();
        map.put("port", managerMapper.selectAuthPorts());
        map.put("answerPort", managerMapper.selectAuthAnswerPorts());
        return map;
    }

    @Override
    public Report selectByPrimaryKey(Report record) {
        Assert.isTrue(record.getClassId()!=null&&record.getClassId()>=1&&record.getClassId()<5,"类型ID异常");
        Assert.isTrue(record.getId() != null,"ID异常");
        Report report = reportMapper.selectByPrimaryKey(record);
        if(report != null){
            DetailsText de = new DetailsText();
            de.setPortId(record.getId());
            de.setClassId(record.getClassId());
            de.setPortIsauth(record.getPortIsauth());
            report.setDetails(detailsTextMapper.selectByPort(de));
        }
        return report;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePortAuth(Report record) {
        Assert.isTrue(record.getClassId()!=null&&record.getClassId()>=1&&record.getClassId()<5,"类型ID异常");
        Assert.isTrue(record.getId() != null,"ID异常");
        if(managerMapper.updatePortAuth(record) > 0){
            DetailsTextQuery detailsTextQuery = new DetailsTextQuery();
            detailsTextQuery.setClassId(record.getClassId());
            detailsTextQuery.setPortId(record.getId());
            detailsTextQuery.setAuthFlag(record.getAuthFlag());
            managerMapper.updatePortAuthDetails(detailsTextQuery);
        }
    }
}
