package com.learn.bookstore.controllers;

import com.learn.bookstore.dto.RegisterUserRequestDTO;
import com.learn.bookstore.dto.RegisterUserResponseDTO;
import com.learn.bookstore.models.User;
import com.learn.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDTO> registerUser(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        try {
            User user = userService.register(registerUserRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    RegisterUserResponseDTO.builder().name(user.getName()).email(user.getEmail()).message("User registration successful!").build()
            );
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    RegisterUserResponseDTO.builder().name("").email("").message("User registration failed!").build()
            );
        }
    }

}
