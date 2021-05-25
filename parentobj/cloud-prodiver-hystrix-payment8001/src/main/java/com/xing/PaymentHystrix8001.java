package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/5/13
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker //服务降级，启用注解
public class PaymentHystrix8001 {

    public static void main(String[] args){
        SpringApplication.run(PaymentHystrix8001.class,args);
    }
}
