package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponseDTO(

        @Schema(description = "Login success message", example = "Login successful.")
        String message,

        @Schema(description = "JWT token generated upon successful authentication", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String jwtToken

) {}