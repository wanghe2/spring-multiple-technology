package com.wang.global;


import com.wang.excption.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandle {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    @ResponseBody
    @ExceptionHandler(MyException.class)
    public String HandleException(HttpServletRequest request, MyException e){
        logger.info("全局异常处理"+e.getMessage());
        return e.getMessage();
    }
}
