package com.learn.bookstore.dto;

import lombok.Builder;

@Builder
public record CategoryRequestDTO(String name, String description) {
}
