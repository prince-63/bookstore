package com.learn.bookstore.dto;

import lombok.Builder;

@Builder
public record RegisterUserResponseDTO(String name, String email, String message) {
}
