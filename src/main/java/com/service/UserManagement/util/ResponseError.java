package com.service.UserManagement.util;

public class ResponseError {
    private String message;
    private String code;

    public ResponseError() {
    }

    public ResponseError(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
