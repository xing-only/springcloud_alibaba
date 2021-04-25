package com.xing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/4/25
 **/
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    private static final String URL = "http://cloud-prodiver-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get")
    public String get(){
        return restTemplate.getForObject(URL + "/payment/get",String.class);
    }
}
