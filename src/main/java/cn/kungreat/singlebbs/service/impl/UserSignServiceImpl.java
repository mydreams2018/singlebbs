package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.UserSign;
import cn.kungreat.singlebbs.mapper.UserSignMapper;
import cn.kungreat.singlebbs.service.UserService;
import cn.kungreat.singlebbs.service.UserSignService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.Date;

@Service
public class UserSignServiceImpl implements UserSignService {
    @Autowired
    private UserSignMapper userSignMapper;
    @Autowired
    private UserService userService;

    @Transactional
    public int insert(UserSign record) {
        record.setLastSignTime(new Date());
        record.setAccumulateSign(1);
        record.setUserAccount(SecurityContextHolder.getContext().getAuthentication().getName());
        return userSignMapper.insert(record);
    }

    @Override
    public UserSign selectByPrimaryKey() {
        UserSign userSign = userSignMapper.selectByPrimaryKey(SecurityContextHolder.getContext().getAuthentication().getName());
        if(userSign != null){
            userSign.setOldAccumulateSign(userSign.getAccumulateSign());
            Date lastSignTime = userSign.getLastSignTime();
            int i = DateUtils.truncatedCompareTo(DateUtils.addDays(lastSignTime, 1),
                    new Date(), Calendar.DATE);
            if(i < 0){
                userSign.setAccumulateSign(0);
            }
            if(i > 0){
                userSign.setCurrentSign(true);
            }
        }
        return userSign;
    }

    @Transactional
    public int updateByPrimaryKey(UserSign record) {
        return userSignMapper.updateByPrimaryKey(record);
    }

    @Transactional
    public void signOn() {
        UserSign userSign = selectByPrimaryKey();
        if(userSign == null){
            insert(new UserSign());
            userService.updateAccumulatePoints(5,SecurityContextHolder.getContext().getAuthentication().getName());
        }else{
            Assert.isTrue(!userSign.getCurrentSign(),"今天已经签到了");
            userSign.setLastSignTime(new Date());
            int num = userSign.getAccumulateSign()==null?1:userSign.getAccumulateSign()+1;
            userSign.setAccumulateSign(num);
            updateByPrimaryKey(userSign);
            int liveNum;
            if(num >= 30){
                liveNum=30;
            }else if(num >= 15){
                liveNum=15;
            }else if(num >= 5){
                liveNum=10;
            }else{
                liveNum=5;
            }
            userService.updateAccumulatePoints(liveNum,userSign.getUserAccount());
        }
    }
}
