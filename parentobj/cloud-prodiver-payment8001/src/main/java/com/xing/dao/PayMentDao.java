package com.xing.dao;

import com.xing.entity.PayMent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Description: dao层
 * @Author DXX
 * @Date 2021/4/16
 **/
@Mapper
public interface PayMentDao {

    public int add(PayMent entity);

    public PayMent getPayMentById(@Param("id") Integer id);

    /**
     * 生成序列号
     *
     * @param platId 平台代码
     * @param seqTypeId 序号类型
     */
    String selectGenerateSequence(@Param("platId") String platId,
                                  @Param("seqTypeId") String seqTypeId);

    void call(@Param("map") Map<String,String> map);

}
