package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO (

        @NotBlank(message = "Username is required")
        @Schema(description = "Username or email of the user", example = "prince@example.com")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
        @Schema(description = "User's account password", example = "securePassword123")
        String password

) { }