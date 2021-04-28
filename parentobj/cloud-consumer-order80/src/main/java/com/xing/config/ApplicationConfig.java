package com.xing.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 注入bean
 * @Author DXX
 * @Date 2021/4/20
 **/
@Configuration
public class ApplicationConfig {

    @Bean
//    @LoadBalanced   //使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    //修改ribbon的负载规则，改为随机
    @Bean
    public IRule myRole(){
        return new RandomRule();
    }
}
