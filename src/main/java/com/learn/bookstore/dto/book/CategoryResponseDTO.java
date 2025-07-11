package com.learn.bookstore.dto.book;

import lombok.Builder;

@Builder
public record CategoryResponseDTO(Long id, String name, String description) {
}
