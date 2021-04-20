package com.xing.controller;

import com.xing.common.CommonResult;
import com.xing.entity.PayMent;
import com.xing.service.PayMentService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        this.payMentService.add(entity);
        return CommonResult.createSuccess();
    }

    @GetMapping("/get/{id}")
    public CommonResult<PayMent> getById(@PathVariable("id") Integer id){
        log.info("获取数据id：" + id);
        PayMent entity = this.payMentService.getPayMentById(id);
        return new CommonResult<PayMent>(200,"success",entity);
    }
}
