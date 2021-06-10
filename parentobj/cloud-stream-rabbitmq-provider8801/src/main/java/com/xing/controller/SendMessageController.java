package com.xing.controller;

import com.xing.service.IMessageProvidre;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 发送消息controller
 * @Author DXX
 * @Date 2021/6/9
 **/
@Slf4j
@RestController
@RequestMapping("/sendMessage")
public class SendMessageController {

    @Resource
    private IMessageProvidre messageProvidre;

    @GetMapping("/send")
    public String send(){
        String send = this.messageProvidre.send();
        return send;
    }
}
