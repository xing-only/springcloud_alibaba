package com.xing.controller;

import com.xing.common.CommonResult;
import com.xing.entity.PayMent;
import com.xing.service.PayMentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
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
        return new CommonResult<PayMent>(200,"success端口号：" + port,entity);
    }

    /**
     * 服务发现，获取注册中心，注册服务的详细信息
     * @date 2021/4/23 11:02
     * @author DXX
     * @param
     * @return com.xing.common.CommonResult
     **/
    @GetMapping("/lb/getDiscovery")
    public CommonResult getDiscovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(str ->{
            log.info(str);
        });
        log.info("端口号：" + port);
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVER");
        instances.forEach(entity ->{
            log.info(entity.getServiceId() + "\t" + entity.getHost() + "\t" + entity.getPort() + "\t" + entity.getUri());
        });

        return new CommonResult(200,"success端口号：" + port,discoveryClient);
    }

    /**
     * feign 读取超时
     * @date 2021/5/10 16:38
     * @author DXX
     * @param
     * @return com.xing.common.CommonResult
     **/
    @GetMapping("/feignTimeOUt")
    public CommonResult feignTimeOUt(){
        //业务处理 需要3瞄准
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CommonResult.createSuccess();
    }
}
