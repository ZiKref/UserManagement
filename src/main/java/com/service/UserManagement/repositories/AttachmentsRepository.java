package com.service.UserManagement.repositories;

import com.service.UserManagement.models.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttachmentsRepository extends JpaRepository<Attachments, UUID> {
    List<Attachments> findByCardId(UUID id);
    boolean existsByVersionOf(UUID versionOf);
}
