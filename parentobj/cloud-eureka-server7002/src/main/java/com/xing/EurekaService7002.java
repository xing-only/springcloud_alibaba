package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/4/22
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaService7002 {
    public static void main(String[] args){
        SpringApplication.run(EurekaService7002.class,args);
    }
}
