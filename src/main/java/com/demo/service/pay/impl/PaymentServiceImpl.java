package com.demo.service.pay.impl;

import com.demo.entity.pay.Payment;
import com.demo.mapper.base.BaseMapper;
import com.demo.mapper.pay.PaymentMapper;
import com.demo.service.base.impl.BaseServiceImpl;
import com.demo.service.pay.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 支付
 *
 * @author yclimb
 * @date 2018/12/10
 */
@Slf4j
@Service("paymentService")
public class PaymentServiceImpl extends BaseServiceImpl<Payment, Integer> implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public BaseMapper<Payment, Integer> getMapper() {
        return paymentMapper;
    }
}
