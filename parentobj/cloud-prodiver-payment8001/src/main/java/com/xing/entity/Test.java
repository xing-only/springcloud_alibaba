package com.xing.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 测试校验
 * @Author DXX
 * @Date 2021/5/6
 **/
@Data
public class Test {

    @NotBlank(message = "名称不能为空")
    private String name;

    private String url;
}
