package com.checker.Exception;

public class CheckException extends RuntimeException{

    int code;

    public CheckException(String message, int code) {
        super(message);
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
