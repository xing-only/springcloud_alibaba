package com.xing.dao;

import com.xing.entity.PayMent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: dao层
 * @Author DXX
 * @Date 2021/4/16
 **/
@Mapper
public interface PayMentDao {

    public int add(PayMent entity);

    public PayMent getPayMentById(@Param("id") Integer id);

}
