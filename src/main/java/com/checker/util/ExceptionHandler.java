package com.checker.util;

import com.checker.Exception.CheckException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = CheckException.class)
    @ResponseBody
    public Map<String,Object> errorHandle(HttpServletRequest req, CheckException e){
        return ResultHandler.handleResult(req.getRequestURL().toString(), e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String,Object> errorHandle(HttpServletRequest req, Exception e){
        return ResultHandler.handleResult(req.getRequestURL().toString(), e);
    }
}
