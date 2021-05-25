package com.xing.controller;

import com.xing.common.CommonResult;
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
 * @Date 2021/5/13
 **/
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/infoOk/{id}")
    public String infoOk(@PathVariable("id") Integer id){
        String result = this.paymentService.info_ok(id);
        log.info("result=" + result);
        return result;
    }

    @GetMapping("/infoError/{id}")
    public String infoError(@PathVariable("id") Integer id){
        String result = this.paymentService.info_error(id);
        log.info("result=" + result);
        return result;
    }
}
