package com.service.UserManagement.util;

public class EmailExistsException extends RuntimeException {

    private final String message;
    private final String email;
    private final String code;

    public EmailExistsException(String email, String code, String message) {
        this.email = email;
        this.code = code;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
