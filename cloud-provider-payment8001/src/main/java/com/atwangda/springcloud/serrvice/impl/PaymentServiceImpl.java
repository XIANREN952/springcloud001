package com.atwangda.springcloud.serrvice.impl;

import com.atwangda.springcloud.dao.PaymentDao;
import com.atwangda.springcloud.entities.Payment;
import com.atwangda.springcloud.serrvice.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public Boolean saveInfo(Payment payment) {
        Integer result = paymentDao.saveInfo(payment);
        return result>0?true:false;
    }

    @Override
    public Payment getPaymentById(Long id) {
        Payment ment = paymentDao.getPaymentById(id);
        return ment;
    }
}
