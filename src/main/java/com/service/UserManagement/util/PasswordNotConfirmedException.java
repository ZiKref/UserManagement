package com.service.UserManagement.util;

public class PasswordNotConfirmedException extends RuntimeException{

    private String password;
    private String confirmPassword;

    public PasswordNotConfirmedException(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
