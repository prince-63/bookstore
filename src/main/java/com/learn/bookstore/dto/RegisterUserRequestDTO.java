package com.learn.bookstore.dto;

import lombok.Builder;

@Builder
public record RegisterUserRequestDTO(String name, String email, String password) {
}
