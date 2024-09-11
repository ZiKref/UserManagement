package com.service.UserManagement.util;

import java.util.UUID;

public class FileNotFoundException extends RuntimeException{

    private UUID id;

    public FileNotFoundException(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
