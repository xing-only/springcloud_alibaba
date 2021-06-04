package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/6/4
 **/
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServer3344 {

    public static void main(String[] args){
        SpringApplication.run(ConfigServer3344.class,args);
    }
}
