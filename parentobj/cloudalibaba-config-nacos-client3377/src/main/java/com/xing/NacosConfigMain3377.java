package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/6/29
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigMain3377 {

    public static void main(String[] args){
        SpringApplication.run(NacosConfigMain3377.class,args);
    }
}
