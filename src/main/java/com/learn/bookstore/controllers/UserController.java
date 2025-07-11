package com.learn.bookstore.controllers;

import com.learn.bookstore.dto.*;
import com.learn.bookstore.dto.user.RegisterUserRequestDTO;
import com.learn.bookstore.dto.user.UserPhoneNumberUpdateRequestDTO;
import com.learn.bookstore.dto.user.UserResponseDTO;
import com.learn.bookstore.dto.user.UserUpdateRequestDTO;
import com.learn.bookstore.mappers.UserResponseMapper;
import com.learn.bookstore.models.user.User;
import com.learn.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> registerUser(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        User user = userService.register(registerUserRequestDTO);
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(UserResponseMapper.toDTO(user));
        response.setSuccess(true);
        response.setMessage("Registration successful.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(UserResponseMapper.toDTO(user));
        response.setSuccess(true);
        response.setMessage("User retrieved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-curr")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getCurrUser(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(UserResponseMapper.toDTO(user));
        response.setSuccess(true);
        response.setMessage("User retrieved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        ResponseDTO<List<UserResponseDTO>> response = new ResponseDTO<>();
        response.setData(users.stream().map(UserResponseMapper::toDTO).toList());
        response.setSuccess(true);
        response.setMessage("All users retrieved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/add-phone")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> addPhoneNumber(Authentication authentication, @RequestBody UserPhoneNumberUpdateRequestDTO phoneNumber) {
        User user = userService.addPhoneNumber(authentication.getName(), phoneNumber.phoneNumber());
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(UserResponseMapper.toDTO(user));
        response.setSuccess(true);
        response.setMessage("Phone number update successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO<String>> deleteUser(Authentication authentication) {
        userService.deleteUser(authentication.getName());
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("User deleted successfully.");
        response.setData(String.format("User deleted successfully: %s", authentication.getName()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> updateUser(Authentication authentication, @RequestBody UserUpdateRequestDTO user) {
        User updatedUser = userService.updateUser(authentication.getName(), user);
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(UserResponseMapper.toDTO(updatedUser));
        response.setSuccess(true);
        response.setMessage("User updated successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
