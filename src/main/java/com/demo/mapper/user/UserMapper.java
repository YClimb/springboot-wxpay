package com.demo.mapper.user;

import com.demo.entity.user.User;
import com.demo.mapper.base.BaseMapper;

public interface UserMapper extends BaseMapper<User, Integer> {

    /**
     * 根据openid查询用户信息
     * @param openid 唯一标识
     * @return user
     *
     * @author yclimb
     * @date 2018/12/10
     */
    User queryByOpenId(String openid);
}