package com.learn.bookstore.dto.book;

import lombok.Builder;

@Builder
public record CategoryRequestDTO(String name, String description) {
}
