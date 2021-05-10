package com.xing.service;

import com.xing.common.CommonResult;
import com.xing.entity.PayMent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVER")
public interface PaymentService {

    @GetMapping("/payment/get/{id}")
    CommonResult<PayMent> getById(@PathVariable("id") Integer id);

    @GetMapping("/payment/feignTimeOUt")
    CommonResult feignTimeOUt();
}
