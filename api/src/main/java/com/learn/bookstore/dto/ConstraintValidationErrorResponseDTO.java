package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstraintValidationErrorResponseDTO {

    @Schema(description = "The path of the API where the error occurred", example = "/api/v1/users/register")
    private String apiPath;

    @Schema(description = "HTTP status code returned for the validation error", example = "400")
    private int errorCode;

    @Schema(description = "General error message describing the failure", example = "Validation failed for request body.")
    private String errorMessage;

    @Schema(description = "The time when the error occurred", example = "2025-07-18T14:35:22")
    private LocalDateTime errorTime;

    @Schema(description = "Map of field names to their respective validation error messages",
            example = "{\"email\": \"must be a well-formed email address\", \"password\": \"must not be blank\"}")
    private Map<String, String> validationErrors;
}
