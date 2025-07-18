package com.learn.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ConstraintValidationErrorResponseDTO {
    private String apiPath;
    private int errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
    private Map<String, String> validationErrors;
}
