package com.xing.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}
