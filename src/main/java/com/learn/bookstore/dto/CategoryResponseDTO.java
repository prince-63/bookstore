package com.learn.bookstore.dto;

import lombok.Builder;

@Builder
public record CategoryResponseDTO(Long id, String name, String description) {
}
