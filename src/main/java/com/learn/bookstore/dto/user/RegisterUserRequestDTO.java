package com.learn.bookstore.dto.user;

import lombok.Builder;

@Builder
public record RegisterUserRequestDTO(String name, String email, String password) {
}
