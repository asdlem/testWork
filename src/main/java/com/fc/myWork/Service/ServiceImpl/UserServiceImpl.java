package com.fc.myWork.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.myWork.Service.UserService;
import com.fc.myWork.mapper.UserMapper;
import com.fc.myWork.model.Entity.User;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String userName) {
        // 根据用户名从数据库中查询用户信息
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", userName));
        if (user == null) {
            throw new UsernameNotFoundException("未找到用户名为 " + userName + " 的用户");
        }
        return user;
    }
}
