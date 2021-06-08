package com.xing.exception;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException;
import com.xing.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/6/7
 **/
@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult exception(Exception e) {
        Throwable cause = e.getCause();
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        if (cause instanceof IllegalArgumentException) {
            return new CommonResult(500,e.getMessage());
        }else if(cause instanceof MySQLSyntaxErrorException){
            String message = e.getMessage();
            if(message.contains("Unknown column")){
                message = message.substring(message.indexOf("'")+1, message.indexOf("'", message.indexOf("'")+1));
            }
            return new CommonResult(500,"未知的列名：" + message);
        }else if(cause instanceof MySQLTransactionRollbackException){
            String message = e.getMessage();
            if(message.contains("Lock wait timeout")){
                message = "事务锁等待超时";
            }
            return new CommonResult(500,message);
        }
        return new CommonResult(500,"系统异常，请联系管理员");
    }
}
