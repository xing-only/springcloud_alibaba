package com.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/5/27
 **/
@SpringBootApplication
@EnableHystrixDashboard //hystrix 服务降级、熔断 图形化界面
public class HystrixDashboard9001 {
    public static void main(String[] args){
        SpringApplication.run(HystrixDashboard9001.class,args);
    }
}
