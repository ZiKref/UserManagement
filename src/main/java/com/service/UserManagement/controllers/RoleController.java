package com.service.UserManagement.controllers;

import com.service.UserManagement.models.Role;
import com.service.UserManagement.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody @Valid Role role) {
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.OK);
    }

}
