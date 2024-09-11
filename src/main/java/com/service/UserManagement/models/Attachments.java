package com.service.UserManagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Attachments")
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "filename")
    private String filename;

    @Column(name = "description")
    private String description;

    @Column(name = "versionOf")
    private UUID versionOf;

    @Column(name = "uploaded")
    private boolean uploaded;

    @Column(name = "cardId")
    private UUID cardId;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    public Attachments() {
    }

    public Attachments(UUID id, String title, String fileName, String description, UUID versionOf,
                       boolean uploaded, UUID cardId, LocalDateTime createdAt, LocalDateTime deletedAt) {
        this.id = id;
        this.title = title;
        this.filename = fileName;
        this.description = description;
        this.versionOf = versionOf;
        this.uploaded = uploaded;
        this.cardId = cardId;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getVersionOf() {
        return versionOf;
    }

    public void setVersionOf(UUID versionOf) {
        this.versionOf = versionOf;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public UUID getCardId() {
        return cardId;
    }

    public void setCardId(UUID cardId) {
        this.cardId = cardId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void setVersionOf(Attachments attachments) {

    }
}
