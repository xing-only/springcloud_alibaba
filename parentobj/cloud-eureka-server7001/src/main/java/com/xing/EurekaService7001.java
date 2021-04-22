package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: eureka-server 服务注册中心
 * @Author DXX
 * @Date 2021/4/22
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaService7001 {

    public static void main(String[] args){
        SpringApplication.run(EurekaService7001.class,args);
    }
}
