package com.service.UserManagement.controllers;

import com.service.UserManagement.dto.PasswordDTO;
import com.service.UserManagement.dto.RoleDTO;
import com.service.UserManagement.dto.UserDTO;
import com.service.UserManagement.models.Status;
import com.service.UserManagement.models.User;
import com.service.UserManagement.services.UserService;
import com.service.UserManagement.util.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : userService.getAllUsers()) {
            userDTOS.add(convertToDTO(user));
        }
        return userDTOS;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") UUID id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(convertToDTO(user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid User user) throws Exception {
        userService.createUser(user);
        return new ResponseEntity<>(convertToDTO(user), HttpStatus.OK);
    }

    @PostMapping("/{id}/set-password")
    public ResponseEntity<UserDTO> setPassword(@RequestBody PasswordDTO newPassword, @PathVariable(name = "id") UUID id) {
        User user = userService.setPassword(id, newPassword);
        return new ResponseEntity<>(convertToDTO(user), HttpStatus.OK);
    }

    @PostMapping("/{id}/set-role")
    public ResponseEntity<UserDTO> setRole(@PathVariable(name = "id") UUID id, @RequestBody RoleDTO roleDTO) {
        User user = userService.setRole(id, roleDTO);
        return new ResponseEntity<>(convertToDTO(user), HttpStatus.OK);
    }

    @PostMapping("/{id}/{state}")
    public ResponseEntity<UserDTO> setStatus(@PathVariable(name = "id") UUID id, @PathVariable(name = "state") Status status) {
        User user = userService.setStatus(id, status);
        return new ResponseEntity<>(convertToDTO(user), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable(name = "id") UUID id) {
        User updateUser = userService.updateUser(userDTO, id);
        return new ResponseEntity<>(convertToDTO(updateUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(name = "id") UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private UserDTO convertToDTO(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    @ExceptionHandler
    public ResponseEntity<FieldErrorDetails> emailExists(EmailExistsException e) {
        FieldErrorDetails error = new FieldErrorDetails();
        error.addFieldError("email", e.getMessage(), e.getCode(), e.getEmail());
        return new ResponseEntity<>(error, HttpStatus.valueOf(422));
    }

    @ExceptionHandler
    public ResponseEntity<ResponseError> userNotFound(UserNotFoundException e) {
        ResponseError error = new ResponseError(
            "UserNotFound",
                "Пользователь с идентификатором " + e.getId() + " не найден"
        );
        return new ResponseEntity<>(error, HttpStatus.valueOf(404));
    }

    @ExceptionHandler
    public ResponseEntity<FieldErrorDetails> passwordNotConfirmed(PasswordNotConfirmedException e) {
        FieldErrorDetails error = new FieldErrorDetails();
        error.addFieldError(
                "password", "Введенные пароли не совпадают", "PasswordNotConfirmed", e.getPassword()
        );
        error.addFieldError(
                "confirmPassword", "Введенные пароли не совпадают", "PasswordNotConfirmed", e.getConfirmPassword()
        );
        return new ResponseEntity<>(error, HttpStatus.valueOf(422));
    }

    @ExceptionHandler
    public ResponseEntity<FieldErrorDetails> roleNotFound(RoleNotFoundException e) {
        FieldErrorDetails role = new FieldErrorDetails();
        role.addFieldError(
                "role", "Указанная роль не существует", "RoleNotFound", e.getNameRole().toString()
        );
        return new ResponseEntity<>(role, HttpStatus.valueOf(422));
    }

}
