package com.demo.controller.user;

import com.demo.core.AppMessage;
import com.demo.entity.user.User;
import com.demo.service.user.UserService;
import com.demo.weixin.sdk.util.WXUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户Controller
 *
 * @author yclimb
 * @date 2018/12/7
 */
@Api
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 根据用户ID获取用户信息
     * @param userId 用户ID
     * @return msg
     *
     * @author yclimb
     * @date 2018/12/10
     */
    @ApiOperation(value = "用户|根据用户ID获取用户信息", httpMethod = "GET", notes = "xxx")
    @GetMapping("/get/{userId}")
    public AppMessage get(@PathVariable Integer userId) {
        if (null == userId) {
            return AppMessage.error(-1);
        }

        // 通过接口取得access_token
        User user = userService.queryById(userId);
        if (user == null) {
            return AppMessage.error(-1);
        }

        // 处理微信昵称emoji表情
        if (StringUtils.isNotBlank(user.getNickName())) {
            // 解码昵称
            user.setNickName(WXUserUtil.decodeNickName(user.getNickName()));
        }

        return AppMessage.success(user);
    }

}
