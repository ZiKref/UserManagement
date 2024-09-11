package com.service.UserManagement.util;

import java.util.HashMap;
import java.util.Map;

public class FieldErrorDetails {

    private Map<String, ErrorDetail> fields = new HashMap<>();

    public Map<String, ErrorDetail> getFields() {
        return fields;
    }

    public void addFieldError(String field, String message, String code, String rejectedValue) {
        fields.put(field, new ErrorDetail(message, code, rejectedValue));
    }

    public static class ErrorDetail {
        private String message;
        private String code;
        private String rejectedValue;

        public ErrorDetail() {
        }

        public ErrorDetail(String message, String code, String rejectedValue) {
            this.message = message;
            this.code = code;
            this.rejectedValue = rejectedValue;
        }

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }

        public String getRejectedValue() {
            return rejectedValue;
        }
    }

}
