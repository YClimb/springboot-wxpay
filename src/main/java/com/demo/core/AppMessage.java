package com.demo.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 前端接收对象
 *
 * @author yclimb
 * @date 2018/12/10
 */
public class AppMessage<T> implements Serializable {

    private static final long serialVersionUID = -6989764551741248050L;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    private static Map<Integer, String> errors = new HashMap<Integer, String>();

    static {
        errors.put(-7, "请求参数不能为空");
        errors.put(-6, "三方请求参数解析异常");
        errors.put(-5, "数据篡改,非法用户标识");
        errors.put(-4, "签名验证失败");
        errors.put(-3, "自定义内容");
        errors.put(-2, "请重新登录");
        errors.put(-1, "系统繁忙，请稍候再试");
        errors.put(0, "操作成功");
        errors.put(1, "系统错误");
        errors.put(503, "服务不可用");

        errors.put(50001, "操作过期，请重新进入页面提交");
        errors.put(50002, "正在处理中，请勿重复提交");
        errors.put(50003, "请勿重复提交");
        errors.put(50004, "不允许伪造提交");

    }

    private AppMessage() {
    }

    private AppMessage(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> AppMessage success(T data) {
        return new AppMessage(0, errors.get(0), data);
    }

    public static AppMessage error(Integer code) {
        return new AppMessage(code, errors.get(code), null);
    }

    public static <T> AppMessage error(Integer code, T data) {
        return new AppMessage(code, errors.get(code), data);
    }

    public static AppMessage custom(String msg) {
        return new AppMessage(-3, msg, null);
    }

    public static <T> AppMessage custom(String msg, T data) {
        return new AppMessage(-3, msg, data);
    }

    public static <T> AppMessage custom(Integer code, T data, String msg) {
        return new AppMessage(code, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
