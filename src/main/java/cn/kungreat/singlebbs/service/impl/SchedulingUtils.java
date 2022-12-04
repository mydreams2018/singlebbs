package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.mapper.CollaborationCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingUtils {
    @Autowired
    private CollaborationCompanyMapper collaborationCompanyMapper;

    @Scheduled(cron = "0 0 1 * * *")
    public void changeCollaborationCompany(){
        collaborationCompanyMapper.updateCollaborationCompany();
    }
}
