package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/6/25
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosPayment9001 {
    public static void main(String[] args){
        SpringApplication.run(NacosPayment9001.class,args);
    }
}
