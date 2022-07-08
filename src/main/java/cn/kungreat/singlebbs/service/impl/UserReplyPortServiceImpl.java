package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.UserReplyPort;
import cn.kungreat.singlebbs.mapper.UserReplyPortMapper;
import cn.kungreat.singlebbs.service.UserReplyPortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
@Service
public class UserReplyPortServiceImpl implements UserReplyPortService {
    @Autowired
    private UserReplyPortMapper userReplyPortMapper;

    @Override
    public List<UserReplyPort> selectAll() {
        UserReplyPort replyPort = new UserReplyPort();
        Calendar instance = Calendar.getInstance();
        replyPort.setReplyYear(instance.get(Calendar.YEAR));
        replyPort.setReplyWeek(instance.get(Calendar.WEEK_OF_YEAR));
        return userReplyPortMapper.selectAll(replyPort);
    }

    @Transactional
    public int updateByPrimaryKey(){
        UserReplyPort record = new UserReplyPort();
        Calendar instance = Calendar.getInstance();
        record.setReplyYear(instance.get(Calendar.YEAR));
        record.setReplyWeek(instance.get(Calendar.WEEK_OF_YEAR));
        record.setUserAccount(SecurityContextHolder.getContext().getAuthentication().getName());
        UserReplyPort replyPort = userReplyPortMapper.selectByPrimaryKey(record);
        if(replyPort == null){
            record.setReplyNumber(1);
            userReplyPortMapper.insert(record);
        }else{
            userReplyPortMapper.updateByPrimaryKey(replyPort);
        }
        return 1;
    }
}
