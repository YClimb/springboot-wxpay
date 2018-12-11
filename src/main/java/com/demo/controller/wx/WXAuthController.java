package com.demo.controller.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.constants.BaseConstants;
import com.demo.core.AppMessage;
import com.demo.entity.user.User;
import com.demo.service.user.UserService;
import com.demo.weixin.sdk.util.WXUserUtil;
import com.demo.weixin.sdk.util.WXUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 微信用户授权控制类
 *
 * @author yclimb
 * @date 2018/7/30
 */
@Api
@Slf4j
@RestController
@RequestMapping("/weixin/auth")
public class WXAuthController {

    @Resource
    private WXUtils wxUtils;

    @Resource
    private UserService userService;

    /**
     * 微信网页授权
     * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
     * 第一步：用户同意授权，获取code
     * 第二步：通过code换取网页授权access_token
     * @return str
     *
     * @author yclimb
     * @date 2018/7/30
     */
    @GetMapping("/authorize")
    @ApiOperation(value = "微信用户|网页授权", httpMethod = "GET", notes = "获取前端微信用户的网页授权，得到用户基础信息")
    public Object authorize(HttpServletRequest request) {

        // 跳转页面标识
        String state = request.getParameter("state");
        // 通过code获取access_token
        String code = request.getParameter("code");
        log.info("authorize:code:{}", code);

        // 获取access_token和openid
        JSONObject jsonToken = wxUtils.getJsapiAccessTokenByCode(code);
        if (null == jsonToken) {
            return AppMessage.error(-2);
        }

        return jsonToken;
    }

    /**
     * 通过access_token和openid请求获取用户信息
     * @return str
     *
     * @author yclimb
     * @date 2018/9/17
     */
    @PostMapping("/getUser/{access_token}/{openid}")
    @ApiOperation(value = "微信用户|悦店精选公众号|通过access_token和openid请求获取用户信息", httpMethod = "POST", notes = "通过access_token和openid请求获取用户信息")
    public Object getUser(@PathVariable String access_token, @PathVariable String openid) {

        // 通过access_token和openid请求获取用户信息
        JSONObject jsonUserinfo = wxUtils.getJsapiUserinfo(access_token, openid);
        if (null == jsonUserinfo) {
            return AppMessage.error(-2);
        }

        // 存储用户信息到数据库
        User user = userService.queryByOpenId(openid);
        if (user == null) {
            user = JSON.parseObject(jsonUserinfo.toJSONString(), User.class);
            user.setCreateDate(new Date());
            user.setIsDel(BaseConstants.N);
            // 处理微信昵称emoji表情
            if (StringUtils.isNotBlank(user.getNickName())) {
                // 编码Base64.decodeBase64()
                user.setNickName(WXUserUtil.encodeNickName(user.getNickName()));
            }

            userService.createEntity(user);
        }

        // 用户名称解码
        user.setNickName(WXUserUtil.decodeNickName(user.getNickName()));
        return AppMessage.success(user);
    }

    /**
     * 根据appid获取wx.config需要的基础参数
     * @param reqMap requestUrl 请求页面地址、appid appid
     * @return json
     *
     * @author yclimb
     * @date 2018/9/25
     */
    @PostMapping("/getSignature")
    @ApiOperation(value = "微信公众号|config需要的基础参数", httpMethod = "POST", notes = "config需要的基础参数")
    public Object getSignature(@RequestBody Map<String, String> reqMap) {
        Map<String, Object> map = wxUtils.getSignature(reqMap.get("requestUrl"), reqMap.get("appid"), BaseConstants.WX_JSAPI_CODE);
        return AppMessage.success(map);
    }

}
