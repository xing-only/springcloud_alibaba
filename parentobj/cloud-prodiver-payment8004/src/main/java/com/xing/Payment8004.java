package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/4/23
 **/
@SpringBootApplication
@EnableDiscoveryClient //该注解用于consul 或者zookeeper 作为注册中心的注册服务
public class Payment8004 {

    public static void main(String[] args){
        SpringApplication.run(Payment8004.class,args);
    }
}
