package com.service.UserManagement.util;

import java.util.UUID;

public class AttachmentNotFoundException extends RuntimeException{

    private UUID versionOf;


    public AttachmentNotFoundException() {
    }

    public AttachmentNotFoundException(UUID versionOf) {
        this.versionOf = versionOf;
    }

    public UUID getVersionOf() {
        return versionOf;
    }

    public void setVersionOf(UUID versionOf) {
        this.versionOf = versionOf;
    }
}
