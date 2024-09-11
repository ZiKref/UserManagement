package com.service.UserManagement.util;

import java.util.UUID;

public class RoleNotFoundException extends RuntimeException{

    private UUID nameRole;

    public RoleNotFoundException(UUID nameRole) {
        this.nameRole = nameRole;
    }

    public UUID getNameRole() {
        return nameRole;
    }

    public void setNameRole(UUID nameRole) {
        this.nameRole = nameRole;
    }
}
