package com.demo.entity.pay;

import com.demo.entity.base.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付交易记录
 * Payment
 * 数据库表：t_payment
 */
@Data
public class Payment extends BaseEntity {

    /**
     * 主键
     * 表字段 : t_payment.id
     */
    private Integer id;

    /**
     * 交易编号：订单id；退款id；提现id
     * 表字段 : t_payment.relation_id
     */
    private Integer relationId;

    /**
     * 流水号
     * 表字段 : t_payment.flow_numer
     */
    private String flowNumer;

    /**
     * 支付类型：1订单；2退款；3提现
     * 表字段 : t_payment.type
     */
    private String type;

    /**
     * 回调类型：11:订单支付；22:团购订单支付；33:订单退款；44:团购订单退款；
     * 表字段 : t_payment.callback_type
     */
    private String callbackType;

    /**
     * 支付渠道：1:微信；2:支付宝；3:银行卡
     * 表字段 : t_payment.capital_channel
     */
    private String capitalChannel;

    /**
     * 付款状态：1:待支付；2:支付成功；-1:支付失败
     * 表字段 : t_payment.status
     */
    private String status;

    /**
     * 金额
     * 表字段 : t_payment.amount
     */
    private BigDecimal amount;

    /**
     * 实际金额
     * 表字段 : t_payment.cash_fee
     */
    private BigDecimal cashFee;

    /**
     * 代金券范围：GLOBAL全场、SINGLE单品、NONE默认，未使用任何券
     * 表字段 : t_payment.coupon_scope
     */
    private String couponScope;

    /**
     * 代金券金额
     * 表字段 : t_payment.coupon_amount
     */
    private BigDecimal couponAmount;

    /**
     * 支付时间
     * 表字段 : t_payment.pay_date
     */
    private Date payDate;

    /**
     * 支付到期时间
     * 表字段 : t_payment.end_pay_date
     */
    private Date endPayDate;

    /**
     * 调用接口时间
     * 表字段 : t_payment.call_date
     */
    private Date callDate;

    /**
     * 支付成功时间
     * 表字段 : t_payment.success_date
     */
    private Date successDate;

    /**
     * 创建时间
     * 表字段 : t_payment.create_date
     */
    private Date createDate;

    /**
     * 创建人id
     * 表字段 : t_payment.create_user_id
     */
    private Integer createUserId;

    /**
     * 创建人name
     * 表字段 : t_payment.create_user_name
     */
    private String createUserName;

    /**
     * 更新时间
     * 表字段 : t_payment.update_date
     */
    private Date updateDate;

    /**
     * 更新人id
     * 表字段 : t_payment.update_user_id
     */
    private Integer updateUserId;

    /**
     * 更新人name
     * 表字段 : t_payment.update_user_name
     */
    private String updateUserName;

    /**
     * 是否删除 默认0
     * 表字段 : t_payment.is_del
     */
    private String isDel;

    /**
     * 微信支付：预支付ID
     * 表字段 : t_payment.prepay_id
     */
    private String prepayId;

    /**
     * 微信支付：终端IP
     * 表字段 : t_payment.spbill_create_ip
     */
    private String spbillCreateIp;

    /**
     * 微信支付：订单描述
     * 表字段 : t_payment.body
     */
    private String body;

    /**
     * 微信支付：签名
     * 表字段 : t_payment.nonce_str
     */
    private String nonceStr;

    /**
     * 微信支付：用户openid
     * 表字段 : t_payment.openid
     */
    private String openid;

    /**
     * 订单优惠标记，用于区分订单是否可以享受优惠
     * 表字段 : t_payment.goods_tag
     */
    private String goodsTag;

    /**
     * 商品详细描述，对于使用单品优惠的商户，该字段必须按照规范上传
     * 表字段 : t_payment.detail
     */
    private String detail;
}