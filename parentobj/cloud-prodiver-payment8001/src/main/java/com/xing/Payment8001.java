package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.swing.*;

/**
 * @Description: 主启动类
 * @Author DXX
 * @Date 2021/4/16
 **/
@SpringBootApplication
@EnableEurekaClient
public class Payment8001 {
    public static void main(String[] args){
        SpringApplication.run(Payment8001.class, args);
    }
}
