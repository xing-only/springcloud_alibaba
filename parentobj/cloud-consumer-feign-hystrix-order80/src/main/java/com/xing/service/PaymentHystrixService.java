package com.xing.service;

import com.xing.service.impl.PaymentHystrixServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * feign 调用
 * @date 2021/5/25 15:01
 * @author DXX
 **/
@Component
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT8001", fallback = PaymentHystrixServiceImpl.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/infoOk/{id}")
    String infoOk(@PathVariable("id") Integer id);

    @GetMapping("/payment/infoError/{id}")
    String infoError(@PathVariable("id") Integer id);
}
