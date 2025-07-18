package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDTO(

        @Schema(description = "The path of the API endpoint where the error occurred", example = "/api/v1/books/99")
        String apiPath,

        @Schema(description = "HTTP status code of the error", example = "NOT_FOUND")
        HttpStatus errorCode,

        @Schema(description = "Descriptive error message", example = "Book with ID 99 not found")
        String errorMessage,

        @Schema(description = "Timestamp when the error occurred", example = "2025-07-18T15:42:11")
        LocalDateTime errorTime

) {}