package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserResponseDTO(

        @Schema(description = "Unique identifier of the user", example = "101")
        Long id,

        @Schema(description = "Full name of the user", example = "Prince Kumar")
        String name,

        @Schema(description = "Email address of the user", example = "prince@example.com")
        String email,

        @Schema(description = "Phone number of the user", example = "+91-9876543210")
        String phone,

        @Schema(description = "Role assigned to the user", example = "ADMIN")
        String role

) {}
