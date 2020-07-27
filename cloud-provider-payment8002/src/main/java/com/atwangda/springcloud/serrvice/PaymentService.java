package com.atwangda.springcloud.serrvice;

import com.atwangda.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public Boolean saveInfo(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
