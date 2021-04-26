package com.xing.controller;

import com.xing.common.CommonResult;
import com.xing.entity.PayMent;
import com.xing.service.PayMentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/4/16
 **/
@Slf4j
@RestController
@RequestMapping("/payment")
public class PayMentController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private PayMentService payMentService;

    /**
     * 新增
     * @date 2021/4/16 17:27
     * @author DXX
     * @param entity
     * @return com.xing.common.CommonResult
     **/
    @PostMapping("/add")
    public CommonResult add(@RequestBody PayMent entity){
        log.info("端口号：" + port);
        this.payMentService.add(entity);
        return CommonResult.createSuccess();
    }

    @GetMapping("/get/{id}")
    public CommonResult<PayMent> getById(@PathVariable("id") Integer id){
        log.info("获取数据id：" + id);
        log.info("端口号：" + port);
        PayMent entity = this.payMentService.getPayMentById(id);
        return new CommonResult<PayMent>(200,"success端口号：" + port,entity);
    }
}
