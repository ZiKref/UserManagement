package com.service.UserManagement.dto;

import com.service.UserManagement.models.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {

    private UUID id;
    private String email;
    private String familyName;
    private String name;
    private String middleName;
    private UUID role;
    private Status status;
    private LocalDateTime createdAt;

    public UserDTO() {
    }

    public UserDTO(UUID id, String email, String familyName, String name, String middleName, UUID role, Status status, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.familyName = familyName;
        this.name = name;
        this.middleName = middleName;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public UUID getRole() {
        return role;
    }

    public void setRole(UUID role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
