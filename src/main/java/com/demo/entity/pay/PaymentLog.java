package com.demo.entity.pay;

import com.demo.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 支付交易记录流水
 * PaymentLog
 * 数据库表：t_payment_log
 */
@Data
public class PaymentLog extends BaseEntity {

    /**
     * 主键
     * 表字段 : t_payment_log.id
     */
    private Integer id;

    /**
     * 资金交易表ID
     * 表字段 : t_payment_log.payment_id
     */
    private Integer paymentId;

    /**
     * 备注，原因
     * 表字段 : t_payment_log.remark
     */
    private String remark;

    /**
     * SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     * 表字段 : t_payment_log.return_code
     */
    private String returnCode;

    /**
     * 返回信息，如非空，为错误原因：签名失败：参数格式校验错误
     * 表字段 : t_payment_log.return_msg
     */
    private String returnMsg;

    /**
     * 微信返回的随机字符串
     * 表字段 : t_payment_log.nonce_str
     */
    private String nonceStr;

    /**
     * 微信返回的签名值
     * 表字段 : t_payment_log.sign
     */
    private String sign;

    /**
     * SUCCESS/FAIL 交易是否成功需要查看result_code来判断
     * 表字段 : t_payment_log.result_code
     */
    private String resultCode;

    /**
     * 错误代码
     * 表字段 : t_payment_log.err_code
     */
    private String errCode;

    /**
     * 错误代码描述
     * 表字段 : t_payment_log.err_code_des
     */
    private String errCodeDes;

    /**
     * 创建时间
     * 表字段 : t_payment_log.create_date
     */
    private Date createDate;

    /**
     * 创建人id
     * 表字段 : t_payment_log.create_user_id
     */
    private Integer createUserId;

    /**
     * 接口返回值
     * 表字段 : t_payment_log.result_map
     */
    private String resultMap;
}