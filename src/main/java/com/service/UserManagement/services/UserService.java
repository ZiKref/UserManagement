package com.service.UserManagement.services;

import com.service.UserManagement.dto.PasswordDTO;
import com.service.UserManagement.dto.RoleDTO;
import com.service.UserManagement.dto.UserDTO;
import com.service.UserManagement.models.Role;
import com.service.UserManagement.models.Status;
import com.service.UserManagement.models.User;
import com.service.UserManagement.repositories.RoleRepository;
import com.service.UserManagement.repositories.UserRepository;
import com.service.UserManagement.util.EmailExistsException;
import com.service.UserManagement.util.PasswordNotConfirmedException;
import com.service.UserManagement.util.RoleNotFoundException;
import com.service.UserManagement.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void createUser(User user) throws Exception {
        // проверка идентификатора существующей роли
        boolean isRoleId = false;
        for (Role role : roleRepository.findAll()) {
            if (role.getId().equals(user.getRole())) {
                isRoleId = true;
                break;
            }
        }
        if (!isRoleId) {
            throw new Exception("gg");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailExistsException(
                    user.getEmail(), "EmailExists", "Пользователь с таким e-mail уже существует"
            );
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.active);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        return userRepository.findById(id).orElseThrow();
    }

    public User updateUser(UserDTO userDTO, UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailExistsException(
                    userDTO.getEmail(), "AnotherEmailExists", "Другой пользователь с таким e-mail уже существует"
            );
        }

        User user = userRepository.findById(id).orElseThrow();
        user.setEmail(userDTO.getEmail());
        user.setFamilyName(userDTO.getFamilyName());
        user.setName(userDTO.getName());
        user.setMiddleName(userDTO.getMiddleName());
        return userRepository.save(user);
    }

    public User setPassword(UUID id, PasswordDTO passwordDTO) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        User user = userRepository.findById(id).orElseThrow();

        if (!passwordDTO.getPassword().equals(passwordDTO.getConfirmPassword())) {
            throw new PasswordNotConfirmedException(passwordDTO.getPassword(), passwordDTO.getConfirmPassword());
        }

        user.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
        return userRepository.save(user);
    }

    public User setRole(UUID id, RoleDTO roleDTO) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        User user = userRepository.findById(id).orElseThrow();

        if (!roleRepository.existsById(roleDTO.getRole())) {
            throw new RoleNotFoundException(roleDTO.getRole());
        }
        Role role = roleRepository.findById(roleDTO.getRole()).orElseThrow();

        user.setRole(role.getId());
        return userRepository.save(user);
    }

    public User setStatus(UUID id, Status status) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        User user = userRepository.findById(id).orElseThrow();

        user.setStatus(status);
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

}
