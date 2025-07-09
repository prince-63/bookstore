package com.learn.bookstore.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDTO(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {

}