package com.service.UserManagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Pattern(regexp = "^[A-Za-zА-Яа-я\\- _]+$")
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "description")
    private String description;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull @Pattern(regexp = "^[A-Za-zА-Яа-я\\- _]+$") String getName() {
        return name;
    }

    public void setName(@NotNull @Pattern(regexp = "^[A-Za-zА-Яа-я\\- _]+$") String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
