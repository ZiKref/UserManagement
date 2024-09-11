package com.service.UserManagement.dto;

import java.util.UUID;

public class AttachmentsDTO {

    private String title;
    private String filename;
    private String description;
    private UUID versionOf;

    public AttachmentsDTO() {
    }

    public AttachmentsDTO(String title, String fileName, String description, UUID versionOf) {
        this.title = title;
        this.filename = fileName;
        this.description = description;
        this.versionOf = versionOf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
}
