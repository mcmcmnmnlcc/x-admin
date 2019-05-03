package com.jeeplus.common.base.exception;

import com.jeeplus.common.myResult.Result;
import com.jeeplus.common.myResult.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        e.printStackTrace();
        logger.info(e.getMessage());
        if (e instanceof org.springframework.web.HttpMediaTypeNotSupportedException) {

            Result result=new Result(false, StatusCode.EXCEPTION_ERROR,"请求参数类型请使用application/json",e.getMessage());
            return result;
        }else if(e instanceof org.springframework.http.converter.HttpMessageNotReadableException){
            Result result=new Result(false, StatusCode.EXCEPTION_ERROR,"请求json数据格式可能错误",e.getMessage());
            return result;
        }
        else {
            Result result=new Result(false, StatusCode.EXCEPTION_ERROR,e.getMessage());
            return result;
        }


    }
}
