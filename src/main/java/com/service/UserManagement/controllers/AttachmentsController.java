package com.service.UserManagement.controllers;

import com.service.UserManagement.dto.AttachmentsDTO;
import com.service.UserManagement.models.Attachments;
import com.service.UserManagement.services.AttachmentsService;
import com.service.UserManagement.util.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AttachmentsController {

    private AttachmentsService attachmentsService;

    @Autowired
    public AttachmentsController(AttachmentsService attachmentsService) {
        this.attachmentsService = attachmentsService;
    }

    @GetMapping("/users/{id}/attachments")
    public ResponseEntity<List<Attachments>> getAllAttachments(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(attachmentsService.getAllAttachments(id), HttpStatus.OK);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Attachments> getFileInfo(@PathVariable(name = "id") UUID id) {
        Attachments attachments = attachmentsService.getFileInfo(id);
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Attachments> downloadFile(@PathVariable(name = "id") UUID id) {
        Attachments attachments = attachmentsService.downloadFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachments.getFilename() + "\"")
                .body(attachments);
    }

    @PostMapping("/users/{id}/attachments")
    public ResponseEntity<Attachments> addAttachments(@PathVariable(name = "id") UUID id, @RequestBody @Valid AttachmentsDTO attachmentsDTO) {
        Attachments attachments = attachmentsService.addAttachments(id, attachmentsDTO);
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }

    @PostMapping("/files/{id}")
    public ResponseEntity<Attachments> addFileAttachments(@PathVariable(name = "id") UUID id) {
        Attachments attachments = attachmentsService.addFileAttachments(id);
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<HttpStatus> deleteFile(@PathVariable(name = "id") UUID id) {
        attachmentsService.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private AttachmentsDTO convertToAttachmentsDTO(Attachments attachments) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(attachments, AttachmentsDTO.class);
    }

    @ExceptionHandler
    public ResponseEntity<FieldErrorDetails> attachmentsNotFound(AttachmentNotFoundException e) {
        FieldErrorDetails error = new FieldErrorDetails();
        error.addFieldError(
                "versionOf", "Файла не существует", "AttachmentNotFound", e.getVersionOf().toString()
        );
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseError> projectNotFound(UserNotFoundException e) {
        ResponseError error = new ResponseError(
                "ProjectNotFound",
                "Карточка проекта с идентификатором " + e.getId() +" не найдена"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseError> fileNotFound(FileNotFoundException e) {
        ResponseError error = new ResponseError(
                "FileNotFound",
                "Файл вложения с идентификатором " + e.getId() + " не найден"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseError> alreadyLoaded(LoadedException e) {
        ResponseError error = new ResponseError(
                "AlreadyLoaded",
                "Файл уже загружен"
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
