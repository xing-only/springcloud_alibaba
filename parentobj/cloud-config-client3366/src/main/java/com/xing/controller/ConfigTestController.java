package com.xing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/6/4
 **/
@Slf4j
@RestController
@RequestMapping("/config")
@RefreshScope //可动态刷新获取的配置属性
public class ConfigTestController {

    @Value("${config.info}")
    private String configData;

    @Value("${server.port}")
    private String ipPort;

    @GetMapping("/confData")
    public String confData(){
        log.info("获取的配置信息:" + configData);
        return configData + "端口号：" + ipPort;
    }
}
