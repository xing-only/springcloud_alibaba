package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/5/28
 **/
@SpringBootApplication
@EnableEurekaClient
public class CloudGateway9527 {

    public static void main(String[] args){
        SpringApplication.run(CloudGateway9527.class,args);
    }
}
