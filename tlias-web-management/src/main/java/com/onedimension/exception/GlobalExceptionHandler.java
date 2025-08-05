package com.onedimension.exception;

import com.onedimension.pojo.Result;
import com.onedimension.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// 全局异常处理
@Slf4j
// RestControllerAdvice: 声明当前类是一个全局异常处理类
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ExceptionHandler: 捕获指定异常 并返回结果
    // 根据方法的异常类型进行对应的异常捕获处理
    @ExceptionHandler
    public Result doException(Exception e) {
        log.error("服务器发生错误: ", e);
        String message = e.getMessage();
        return ResultUtil.fail(!message.isEmpty() ? message : "服务器异常, 请稍后再试");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据异常: ", e);

        String message = e.getMessage();
        String errorMsg = message.substring(message.indexOf("Duplicate entry"));
        String errorKey = errorMsg.split(" ")[2];
        return ResultUtil.fail(errorKey + "已存在");
    }

}
