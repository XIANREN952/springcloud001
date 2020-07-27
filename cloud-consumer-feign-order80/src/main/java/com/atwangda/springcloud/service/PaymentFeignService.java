package com.atwangda.springcloud.service;

import com.atwangda.springcloud.entities.Payment;
import com.atwangda.springcloud.entities.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    //将需要调用的8001端口方法在这里进行声明
    @PostMapping(value = "/payment/savepaymentinfo")
    public Result<String> savePaymentInfo(@RequestBody Payment payment);

    //将需要调用的8001端口方法在这里进行声明
    @GetMapping(value = "/payment/getPaymentInfo/{id}")
    public  Result getPaymentById(@PathVariable("id") Long id);

}
