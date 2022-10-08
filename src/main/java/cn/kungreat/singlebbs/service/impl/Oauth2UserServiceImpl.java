package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.Oauth2User;
import cn.kungreat.singlebbs.domain.User;
import cn.kungreat.singlebbs.mapper.Oauth2UserMapper;
import cn.kungreat.singlebbs.service.Oauth2UserService;
import cn.kungreat.singlebbs.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Oauth2UserServiceImpl implements Oauth2UserService {
    @Autowired
    private Oauth2UserMapper oauth2UserMapper;
    @Autowired
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    public int insert(Oauth2User record,User user){
        oauth2UserMapper.insert(record);
        userService.insert(user);
        return 1;
    }

    @Override
    public User selectByPrimaryKey(String openId) {
        Oauth2User oauth2User = oauth2UserMapper.selectByPrimaryKey(openId);
        if(oauth2User != null && StringUtils.isNotEmpty(oauth2User.getUserAccount())){
            return userService.selectByPrimaryKey(oauth2User.getUserAccount());
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Oauth2User record) {
        return oauth2UserMapper.updateByPrimaryKey(record);
    }
}
