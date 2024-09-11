package com.service.UserManagement.dto;

import java.util.UUID;

public class RoleDTO {

    private UUID role;

    public RoleDTO() {
    }

    public RoleDTO(UUID role) {
        this.role = role;
    }

    public UUID getRole() {
        return role;
    }

    public void setRole(UUID role) {
        this.role = role;
    }
}
