package com.atwangda.springcloud.controller;

import com.atwangda.myrule.MySelfRule;
import com.atwangda.springcloud.entities.Payment;
import com.atwangda.springcloud.entities.Result;
import com.atwangda.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/savePayment")
    public Result savePayment(Payment payment){
        log.info("消费者模块调用成功1");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/savepaymentinfo",payment,Result.class);
    }

    @GetMapping("/consumer/payment/getPayment/{id}")
    public Result getPayment(@PathVariable("id") Long id){
        log.info("消费者模块调用成功2");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentInfo/"+id,Result.class);
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instanceList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instanceList == null || instanceList.size() <= 0){
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instances(instanceList);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri + "/payment/lb",String.class);
    }
}
