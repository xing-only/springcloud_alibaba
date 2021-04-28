package com.xing.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.xing.common.CommonResult;
import com.xing.entity.PayMent;
import com.xing.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @Description: 支付
 * @Author DXX
 * @Date 2021/4/20
 **/
@Slf4j
@RestController
    @RequestMapping("/order")
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVER";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

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

    /**
     * 自定义轮询
     * @date 2021/4/28 16:42
     * @author DXX
     * @param id
     * @return com.xing.common.CommonResult<com.xing.entity.PayMent>
     **/
    @GetMapping("mylb/get/{id}")
    public CommonResult<PayMent> getMyById(@PathVariable("id") Integer id){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVER");
        if(CollectionUtil.isNotEmpty(instances)){
            ServiceInstance serviceInstance = loadBalancer.instance(instances);
            URI uri = serviceInstance.getUri();
            return restTemplate.getForObject(uri + "/payment/get/" + id,CommonResult.class);
        }else{
            return null;
        }
    }
}
