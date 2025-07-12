package com.learn.bookstore.dto;

import lombok.Builder;

@Builder
public record UserResponseDTO(Long id, String name, String email, String phone, String role) {
}
