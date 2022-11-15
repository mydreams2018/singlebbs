package cn.kungreat.singlebbs.service.impl;

import cn.kungreat.singlebbs.domain.User;
import cn.kungreat.singlebbs.mapper.UserMapper;
import cn.kungreat.singlebbs.query.UserQuery;
import cn.kungreat.singlebbs.service.UserService;
import cn.kungreat.singlebbs.util.UserAccumulate;
import cn.kungreat.singlebbs.vo.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Value("#{'${user.manager}'.split(',')}")
    private List<String> manager;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Transactional(rollbackFor = Exception.class)
    public int insert(User record) {
        String s = record.validMessage();
        Assert.isTrue(StringUtils.isEmpty(s),s);
        Assert.isTrue(!manager.contains(record.getAccount()),"此用户不能注册");
        Assert.isTrue(userMapper.selectByunique(record.getAccount(),record.getAlias())==null,"用户或别名已经存在");
        record.setRegisterTime(new Date());
        Calendar c = Calendar.getInstance();
        record.setRegisterYear(c.get(Calendar.YEAR));
        record.setPassword(bCryptPasswordEncoder.encode(record.getPassword()));
        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(String account) {
        return userMapper.selectByPrimaryKey(account);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(User record) {
        Assert.isTrue(StringUtils.isNotEmpty(record.getEmail())
                && StringUtils.isNotEmpty(record.getFromCity())
                && StringUtils.isNotEmpty(record.getDescription()),"必要数据为空");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        record.setAccount(name);
        if(StringUtils.isNotEmpty(selectByPrimaryKey(name).getEmail())){
            record.setEmail(null);
        }else{
            record.setEmail(bCryptPasswordEncoder.encode(record.getEmail()));
        }
        return userMapper.updateByPrimaryKey(record);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateImg(String account, String path) {
        return userMapper.updateImg(account,path);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateAccumulatePoints(int number, String account) {
        User user = userMapper.selectByPrimaryKey(account);
        Integer accumulatePoints = user.getAccumulatePoints();
        int current = accumulatePoints + number;
        Assert.isTrue(current >= 0,"飞吻数据不足");
        return userMapper.updateAccumulatePoints(current, accumulatePoints,account, UserAccumulate.countVipLevel(current));
    }

    @Override
    public User selectByunique(String account, String alias) {
        return userMapper.selectByunique(account,alias);
    }

    @Transactional(rollbackFor = Exception.class)
    public void rePass(User user) {
        String s = user.validPass();
        Assert.isTrue(StringUtils.isEmpty(s),s);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User origin = userMapper.selectByPrimaryKey(name);
        Assert.isTrue(bCryptPasswordEncoder.matches(user.getPassword(),origin.getPassword()),"旧密码验证失败");
        userMapper.repass(name,bCryptPasswordEncoder.encode(user.getRePass()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(User user) {
        Assert.isTrue(StringUtils.isNotEmpty(user.getAccount())&&StringUtils.isNotEmpty(user.getEmail())
                                &&StringUtils.isNotEmpty(user.getRePass()),"必要数据不能为空");
        String s = user.validPass();
        Assert.isTrue(StringUtils.isEmpty(s),s);
        User origin = userMapper.selectByPrimaryKey(user.getAccount());
        Assert.isTrue(origin!=null,"当前用户不存在");
        Assert.isTrue(StringUtils.isNotEmpty(origin.getEmail()),"当前用户密保不存在");
        Assert.isTrue(bCryptPasswordEncoder.matches(user.getEmail(),origin.getEmail()),"密保验证失败");
        userMapper.repass(user.getAccount(),bCryptPasswordEncoder.encode(user.getRePass()));
    }

    @Override
    public QueryResult getAllUser(UserQuery userQuery) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Assert.isTrue(manager.contains(name),"没有权限查询此接口");
        int num = userMapper.selectCount(userQuery);
        List list  = Collections.emptyList();
        if(num > 0){
            list = userMapper.selectAll(userQuery);
        }
        userQuery.setData(num,userQuery.getPageSize(),userQuery.getCurrentPage());
        QueryResult result = new QueryResult();
        result.setDatas(list);
        result.setPage(userQuery);
        return result;
    }
}