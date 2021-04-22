package com.xing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 实体类(订单)
 * @Author DXX
 * @Date 2021/4/16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayMent implements Serializable {

    private Integer  id;

    private String serial;
}
