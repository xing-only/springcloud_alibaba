package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: 主启动类
 * @Author DXX
 * @Date 2021/4/16
 **/
@SpringBootApplication
@EnableEurekaClient
public class Payment8002 {
    public static void main(String[] args){
        SpringApplication.run(Payment8002.class, args);
    }
}
