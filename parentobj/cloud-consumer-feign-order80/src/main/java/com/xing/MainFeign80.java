package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 朱启动类
 * @Author DXX
 * @Date 2021/5/10
 **/
@SpringBootApplication
@EnableFeignClients
public class MainFeign80 {

    public static void main(String[] args){
        SpringApplication.run(MainFeign80.class,args);
    }
}
