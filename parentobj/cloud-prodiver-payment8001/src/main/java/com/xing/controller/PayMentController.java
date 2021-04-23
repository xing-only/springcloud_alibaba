package com.xing.controller;

import com.xing.common.CommonResult;
import com.xing.entity.PayMent;
import com.xing.service.PayMentService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private DiscoveryClient discoveryClient;

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
        return new CommonResult<PayMent>(200,"success",entity);
    }

    @GetMapping("/getDiscovery")
    public CommonResult getDiscovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(str ->{
            log.info(str);
        });

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVER");
        instances.forEach(entity ->{
            log.info(entity.getServiceId() + "\t" + entity.getHost() + "\t" + entity.getPort() + "\t" + entity.getUri());
        });

        return new CommonResult(200,"success",discoveryClient);
    }
}
