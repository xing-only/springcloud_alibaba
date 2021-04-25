package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/4/25
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class Order80 {

    public static void main(String[] args){
        SpringApplication.run(Order80.class,args);
    }
}
