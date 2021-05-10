package com.xing.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xing.common.CommonResult;
import com.xing.entity.PayMent;
import com.xing.entity.Test;
import com.xing.service.PayMentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import sun.net.www.protocol.https.Handler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/4/16
 **/
@Slf4j
@RestController
@RequestMapping("/payment")
public class PayMentController {
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

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
        return new CommonResult<PayMent>(200,"success端口号：" + port,entity);
    }

    /**
     * 服务发现，获取注册中心，注册服务的详细信息
     * @date 2021/4/23 11:02
     * @author DXX
     * @param
     * @return com.xing.common.CommonResult
     **/
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

    @GetMapping("/create")
    public void create(){
        ExecutorService service = Executors.newFixedThreadPool(100);
        Set<String> list = new HashSet<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 10; i++){
                        String s = payMentService.generateSequence("P00001", "A01");
                        list.add(s);
                    }
                }
            });
        }
        service.shutdown();
        while(true){
            if(service.isTerminated()){
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("总共用时：" + (end - start));
        System.out.println(list.size());
    }

    /**
     * 数据校验
     * @date 2021/5/6 16:12
     * @author DXX
     * @param
     * @return void
     **/
    @GetMapping("/validate")
    public void validaTest(){
        Test t = new Test();
        t.setName("hello");
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Test>> constraintViolations = validator.validate(t);
        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<Test> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
    }

}
