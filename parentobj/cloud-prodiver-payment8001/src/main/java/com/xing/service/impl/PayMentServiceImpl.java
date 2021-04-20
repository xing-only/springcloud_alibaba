package com.xing.service.impl;

import com.xing.dao.PayMentDao;
import com.xing.entity.PayMent;
import com.xing.service.PayMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/4/16
 **/
@Service("payMentService")
public class PayMentServiceImpl implements PayMentService {

    @Autowired
    private PayMentDao payMentDao;

    @Override
    public void add(PayMent entity) {
        this.payMentDao.add(entity);
    }

    @Override
    public PayMent getPayMentById(Integer id) {
        PayMent entity = this.payMentDao.getPayMentById(id);
        return entity;
    }
}
