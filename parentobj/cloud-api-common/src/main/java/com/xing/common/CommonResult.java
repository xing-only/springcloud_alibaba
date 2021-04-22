package com.xing.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 返回的封装体
 * @Author DXX
 * @Date 2021/4/16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;

    private String message;

    private T data;

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }

    public static CommonResult createSuccess(){
        return new CommonResult(200,"success");
    }
}
