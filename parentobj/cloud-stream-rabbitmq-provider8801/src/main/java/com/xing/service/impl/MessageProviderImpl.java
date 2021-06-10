package com.xing.service.impl;

import com.xing.service.IMessageProvidre;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Description: 发送消息实现类
 * @Author DXX
 * @Date 2021/6/9
 **/
@EnableBinding(Source.class) //定义消息的发送管道
public class MessageProviderImpl implements IMessageProvidre {

    @Resource
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("**********serial:" + serial);
        return serial;
    }
}
