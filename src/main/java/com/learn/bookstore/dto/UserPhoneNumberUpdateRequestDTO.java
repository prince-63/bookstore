package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserPhoneNumberUpdateRequestDTO(

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Phone number must be a valid 10-digit Indian mobile number"
        )
        @Schema(
                description = "User's 10-digit phone number (Indian format)",
                example = "9876543210"
        )
        String phoneNumber

) {}