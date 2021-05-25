package com.xing.service.impl;

import com.xing.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/5/25
 **/
@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {

    @Override
    public String infoOk(Integer id) {
        return "----------infoOk fallback----------";
    }

    @Override
    public String infoError(Integer id) {
        return "----------infoError fallback----------";
    }
}
