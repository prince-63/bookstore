package com.learn.bookstore.dto.user;

import lombok.Builder;

@Builder
public record UserResponseDTO(Long id, String name, String email, String phone, String role) {
}
