package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterUserRequestDTO(

        @NotEmpty(message = "Name is required")
        @Schema(description = "Full name of the user", example = "Prince Kumar Prasad")
        String name,

        @NotEmpty(message = "Email is required")
        @Email(message = "Email format is invalid")
        @Schema(description = "Valid email address of the user", example = "prince@example.com")
        String email,

        @NotEmpty(message = "Password is required")
        @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
        @Schema(description = "Password for user account", example = "securePassword123")
        String password

) { }
