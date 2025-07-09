package com.learn.bookstore.dto.user;

import lombok.Builder;

@Builder
public record UserUpdateRequestDTO(String name, String email, String phone) {
}
