package com.atwangda.springcloud.controller;

import com.atwangda.springcloud.entities.Payment;
import com.atwangda.springcloud.entities.Result;
import com.atwangda.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/savePayment")
    public Result savePayment(Payment payment){
        log.info("消费者模块调用成功1");
        return paymentFeignService.savePaymentInfo(payment);
    }

    @GetMapping("/consumer/payment/getPayment/{id}")
    public Result getPayment(@PathVariable("id") Long id){
        log.info("消费者模块调用成功2");
        return paymentFeignService.getPaymentById(id);
    }
}
