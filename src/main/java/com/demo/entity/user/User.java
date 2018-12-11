package com.demo.entity.user;

import com.demo.entity.base.BaseEntity;
import lombok.Data;

/**
 * 用户信息表
 * User
 * 数据库表：t_user
 */
@Data
public class User extends BaseEntity {

    /**
     * 主键
     * 表字段 : t_user.id
     */
    private Integer id;

    /**
     * 微信：用户的唯一标识
     * 表字段 : t_user.openid
     */
    private String openid;

    /**
     * 微信：用户昵称
     * 表字段 : t_user.nick_name
     */
    private String nickName;

    /**
     * 微信：用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * 表字段 : t_user.sex
     */
    private String sex;

    /**
     * 微信：用户个人资料填写的省份
     * 表字段 : t_user.province
     */
    private String province;

    /**
     * 微信：普通用户个人资料填写的城市
     * 表字段 : t_user.city
     */
    private String city;

    /**
     * 微信：国家，如中国
     * 表字段 : t_user.country
     */
    private String country;

    /**
     * 微信：用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * 表字段 : t_user.headimgurl
     */
    private String headimgurl;

    /**
     * 微信：用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     * 表字段 : t_user.privilege
     */
    private String privilege;

    /**
     * 微信：只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * 表字段 : t_user.unionid
     */
    private String unionid;

}