package com.nhc.Identity_service.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhc.Identity_service.entity.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private int code = 1000;
    private String message;
    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
