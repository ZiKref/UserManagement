package com.service.UserManagement.util;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    private final UUID id;

    public UserNotFoundException(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
