package com.service.UserManagement.services;

import com.service.UserManagement.dto.AttachmentsDTO;
import com.service.UserManagement.models.Attachments;
import com.service.UserManagement.repositories.AttachmentsRepository;
import com.service.UserManagement.repositories.UserRepository;
import com.service.UserManagement.util.AttachmentNotFoundException;
import com.service.UserManagement.util.FileNotFoundException;
import com.service.UserManagement.util.LoadedException;
import com.service.UserManagement.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentsService {

    private final UserRepository userRepository;
    private final AttachmentsRepository attachmentsRepository;

    @Autowired
    public AttachmentsService(AttachmentsRepository attachmentsRepository, UserRepository userRepository) {
        this.attachmentsRepository = attachmentsRepository;
        this.userRepository = userRepository;
    }

    public List<Attachments> getAllAttachments(UUID id) {
        return attachmentsRepository.findByCardId(id);
    }

    public Attachments getFileInfo(UUID id) {
        if (!attachmentsRepository.existsById(id)) {
            throw new FileNotFoundException(id);
        }
        return attachmentsRepository.findById(id).orElseThrow();
    }

    public Attachments downloadFile(UUID id) {
        if (!attachmentsRepository.existsById(id)) {
            throw new FileNotFoundException(id);
        }
        Attachments attachments = attachmentsRepository.findById(id).orElseThrow();
        if (attachments.isUploaded() == false) {
            throw new FileNotFoundException(id);
        }
        if (attachments.getDeletedAt() != null) {
            throw new FileNotFoundException(id);
        }
        return attachments;
    }

    public Attachments addAttachments(UUID id, AttachmentsDTO attachmentsDTO) {
        Attachments attachments = new Attachments();
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        attachments.setCardId(id);
        if (attachmentsDTO.getVersionOf() == null) {
            attachments.setVersionOf(UUID.randomUUID());
        } else if (attachmentsRepository.existsByVersionOf(attachmentsDTO.getVersionOf())) {
            attachments.setVersionOf(attachmentsDTO.getVersionOf());
        } else {
            throw new AttachmentNotFoundException(attachmentsDTO.getVersionOf());
        }
        attachments.setTitle(attachmentsDTO.getTitle());
        attachments.setFilename(attachmentsDTO.getFilename());
        attachments.setDescription(attachmentsDTO.getDescription());
        attachments.setCreatedAt(LocalDateTime.now());
        attachments.setUploaded(false);

        attachmentsRepository.save(attachments);
        return attachments;
    }

    public Attachments addFileAttachments(UUID id) {
        if (!attachmentsRepository.existsById(id)) {
            throw new FileNotFoundException(id);
        }
        Attachments attachments = attachmentsRepository.findById(id).orElseThrow();
        if (attachments.isUploaded()) {
            throw new LoadedException();
        }
        attachments.setUploaded(true);
        return attachmentsRepository.save(attachments);
    }

    public void deleteFile(UUID id) {
        if (!attachmentsRepository.existsById(id)) {
            throw new FileNotFoundException(id);
        }
        Attachments attachments = attachmentsRepository.findById(id).orElseThrow();
        if (attachments.isUploaded() == true) {
            attachments.setDeletedAt(LocalDateTime.now());
            attachmentsRepository.save(attachments);
        }
        if (attachments.isUploaded() == false) {
            attachmentsRepository.deleteById(id);
        }
    }

}
