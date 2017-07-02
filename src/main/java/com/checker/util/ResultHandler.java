package com.checker.util;

import com.checker.Exception.CheckException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultHandler {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    public static Map<String, Object> handleResult(String url, Exception e){
        Map<String, Object> result = new HashMap<>();
        result.put("result", ERROR);
        result.put("url", url);
        result.put("message", getException(e));
        return result;
    }

    public static Map<String, Object> handleResult(String url, CheckException e){
        Map<String, Object> result = new HashMap<>();
        result.put("result", ERROR);
        result.put("url", url);
        result.put("message", getException(e));
        return result;
    }

    public static Map<String, Object> handleResult(List<Object> data){
        Map<String, Object> result = new HashMap<>();
        result.put("result", SUCCESS);
        result.put("data", data);
        return result;
    }

    public static Map<String, Object> handleResult(List<String> same, Double sameRate, Double similarRate, int needStep, int srclen){
        Map<String, Object> result = new HashMap<>();
        result.put("result", SUCCESS);
        result.put("data", getResult(same, sameRate, similarRate, needStep, srclen));
        return result;
    }

    public static Map<String,Object> getResult(List<String> same, Double sameRate, Double similarRate, int needStep, int srclen){
        Map<String,Object> result = new HashMap<>();
        result.put("sameList",same);
        result.put("sameRate",sameRate);
        result.put("similarRate",similarRate);
        result.put("needStep",needStep);
        result.put("sourceLength",srclen);
        return result;
    }

    public static Map<String,Object> getException(CheckException e){
        Map<String,Object> result = new HashMap<>();
        result.put("code" , e.getCode());
        result.put("error_message",e.getLocalizedMessage());
        return result;
    }

    public static Map<String,Object> getException(Exception e){
        Map<String,Object> result = new HashMap<>();
        result.put("code" , 100);
        result.put("error_message",e);
        return result;
    }
}
