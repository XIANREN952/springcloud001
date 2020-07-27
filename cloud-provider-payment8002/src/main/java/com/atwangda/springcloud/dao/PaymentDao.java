package com.atwangda.springcloud.dao;

import com.atwangda.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PaymentDao {

    public int saveInfo(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
