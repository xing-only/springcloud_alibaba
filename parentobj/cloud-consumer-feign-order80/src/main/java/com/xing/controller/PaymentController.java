package com.xing.controller;

import com.xing.common.CommonResult;
import com.xing.entity.PayMent;
import com.xing.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/5/10
 **/
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    /**
     * 调用支付提供者
     * @date 2021/4/20 15:41
     * @author DXX
     * @param id
     * @return com.xing.common.CommonResult<com.xing.entity.PayMent>
     **/
    @GetMapping("/get/{id}")
    public CommonResult<PayMent> getById(@PathVariable("id") Integer id){
        return paymentService.getById(id);
    }

    /**
     * 超时请求
     * @date 2021/5/10 16:40
     * @author DXX
     * @param
     * @return com.xing.common.CommonResult<com.xing.entity.PayMent>
     **/
    @GetMapping("/feignTimeOUt")
    public CommonResult<PayMent> feignTimeOUt(){
        return paymentService.feignTimeOUt();
    }
}
