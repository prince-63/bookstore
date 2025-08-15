package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserUpdateRequestDTO(

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        @Schema(description = "Full name of the user", example = "Prince Kumar Prasad")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email format is invalid")
        @Schema(description = "Valid email address of the user", example = "prince@example.com")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Phone number must be a valid 10-digit Indian mobile number"
        )
        @Schema(description = "10-digit phone number (Indian format)", example = "9876543210")
        String phone

) {}
