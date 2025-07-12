package com.learn.bookstore.dto;

import lombok.Builder;

@Builder
public record UserUpdateRequestDTO(String name, String email, String phone) {
}
