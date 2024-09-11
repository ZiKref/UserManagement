package com.service.UserManagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @NotNull
    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull
    @Pattern(regexp = "^[A-Za-zА-Яа-я\\-]+$")
    @Column(name = "familyName", nullable = false)
    private String familyName;

    @NotNull
    @Pattern(regexp = "^[A-Za-zА-Яа-я\\-]+$")
    @Column(name = "name", nullable = false)
    private String name;

    @Pattern(regexp = "^[A-Za-zА-Яа-я\\-]+$")
    @Column(name = "middleName")
    private String middleName;

    @NotNull
    @Column(name = "role", nullable = false)
    private UUID role;

    @NotNull
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public User() {
    }

    public User(UUID id, String email, String familyName, String name, String middleName,
                UUID role_id, String password, Status status, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.familyName = familyName;
        this.name = name;
        this.middleName = middleName;
        this.role = role_id;
        this.password = password;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
