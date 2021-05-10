package com.xing.service;

import com.xing.entity.PayMent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author DXX
 * @Date 2021/4/16
 **/
public interface PayMentService {

    public void add(PayMent entity);

    public PayMent getPayMentById(@Param("id") Integer id);

    /**
     * 生成序列号
     *
     * @param platId 平台代码
     * @param seqTypeId 序号类型
     */
    String generateSequence(String platId, String seqTypeId);
}
