package com.xing.controller;

import com.xing.common.CommonResult;
import com.xing.entity.PayMent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 支付
 * @Author DXX
 * @Date 2021/4/20
 **/
@Slf4j
@RestController
    @RequestMapping("/order")
public class OrderController {

    public static final String PAYMENT_URL = "http://cloud-payment-server";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 调用支付提供者
     * @date 2021/4/20 15:33
     * @author DXX
     * @param entity
     * @return com.xing.common.CommonResult
     **/
    @PostMapping("/add")
    public CommonResult add(@RequestBody PayMent entity){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add",entity,CommonResult.class);
    }

    /**
     * 调用支付提供者
     * @date 2021/4/20 15:41
     * @author DXX
     * @param id
     * @return com.xing.common.CommonResult<com.xing.entity.PayMent>
     **/
    @GetMapping("/get/{id}")
    public CommonResult<PayMent> getById(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);
    }
}
