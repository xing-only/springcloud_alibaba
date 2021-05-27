package com.xing.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/5/13
 **/
@Service
public class PaymentService {

    public String info_ok(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "-------------info_ok,id=" + id;
    }

    /**
     * 当服务响应超时，超过3秒，会执行 method_timeoutHandler 方法
     * @date 2021/5/25 15:42
     * @author DXX
     * @param id
     * @return java.lang.String
     **/
    @HystrixCommand(fallbackMethod = "method_timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String info_error(Integer id){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "--------------info_error耗时三秒钟,id=" + id;
    }

    /**
     *
     * @date 2021/5/25 15:41
     * @author DXX
     * @param id
     * @return java.lang.String
     **/
    public String method_timeoutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "--------------系统繁忙，请稍后再试。。。method_timeoutHandler,id=" + id;
    }

    /**
     * 服务熔断(开始熔断器，在10秒以内请求，错误率超过60%，则开启熔断，服务降级；如果成功率上升，慢慢放开熔断器，半开状态)
     * @date 2021/5/27 14:46
     * @author DXX
     * @param id
     * @return java.lang.String
     **/
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("**********id 不能为负数");
        }
        String uuid = IdUtil.simpleUUID(); //生成uuid，没有横线
        return Thread.currentThread().getName() + "---调用成功，流水号：" + uuid;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试！！！！id: " +id;
    }
}
