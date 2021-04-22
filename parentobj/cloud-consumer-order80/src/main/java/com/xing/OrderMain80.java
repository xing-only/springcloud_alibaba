package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 主启动
 * @Author DXX
 * @Date 2021/4/20
 **/
@SpringBootApplication
@EnableEurekaClient
public class OrderMain80 {

    public static void main(String[] args){
        SpringApplication.run(OrderMain80.class, args);
    }
}
