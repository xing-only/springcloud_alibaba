package com.xing.service.impl;

import com.xing.dao.PayMentDao;
import com.xing.entity.PayMent;
import com.xing.service.PayMentService;
import org.apache.commons.lang.Validate;
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
    /**
     * 生成序列号
     *
     * @param platId    平台代码
     * @param seqTypeId 序号类型
     */
    @Override
    public String generateSequence(String platId, String seqTypeId) {
        Validate.notEmpty(platId, "平台代码不能为空");
        Validate.notEmpty(seqTypeId, "序号类型不能为空");
        String rs = payMentDao.selectGenerateSequence(platId, seqTypeId);
        Validate.notEmpty(rs, "序列号生成失败");
        return rs;
    }

}
