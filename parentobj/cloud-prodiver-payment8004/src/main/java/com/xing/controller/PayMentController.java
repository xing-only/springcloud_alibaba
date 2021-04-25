package com.xing.controller;

import com.xing.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/4/23
 **/
@RestController
@Slf4j
@RequestMapping("/payment")
public class PayMentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/get")
    public String get(){

        return "ZK:" + port + "\t" + UUID.randomUUID().toString();
    }
}
