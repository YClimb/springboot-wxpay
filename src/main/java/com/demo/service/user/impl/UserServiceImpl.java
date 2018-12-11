package com.demo.service.user.impl;

import com.demo.entity.user.User;
import com.demo.mapper.base.BaseMapper;
import com.demo.mapper.user.UserMapper;
import com.demo.service.base.impl.BaseServiceImpl;
import com.demo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户ServiceImpl
 *
 * @author yclimb
 * @date 2018/12/7
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public BaseMapper<User, Integer> getMapper() {
        return userMapper;
    }

    @Override
    public User queryByOpenId(String openid) {
        return userMapper.queryByOpenId(openid);
    }
}
