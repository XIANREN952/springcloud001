package com.atwangda.springcloud.controller;

import com.atwangda.springcloud.entities.Payment;
import com.atwangda.springcloud.serrvice.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.atwangda.springcloud.entities.Result;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/savepaymentinfo")
    public Result<String> savePaymentInfo(@RequestBody Payment payment){
        Boolean isAchie = paymentService.saveInfo(payment);
        if(isAchie){
            log.info("保存成功");
            return  new Result<String>(200,"保存成功,serverPort:"+serverPort,"1");
        }else{
            log.info("保存失败");
            return  new Result<String>(500,"保存失败,serverPort:"+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/getPaymentInfo/{id}")
    public  Result getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:"+payment.toString());
        if(payment != null){
            return  new Result(200,"查询成功,serverPort:"+serverPort,payment);
        }else {
            return  new Result(500,"查询失败,serverPort:"+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

}
