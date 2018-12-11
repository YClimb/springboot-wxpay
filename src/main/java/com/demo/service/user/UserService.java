package com.demo.service.user;

import com.demo.entity.user.User;
import com.demo.service.base.BaseService;

/**
 * 用户Service
 *
 * @author yclimb
 * @date 2018/12/7
 */
public interface UserService extends BaseService<User, Integer> {

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
