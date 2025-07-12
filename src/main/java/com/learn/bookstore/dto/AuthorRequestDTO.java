package com.learn.bookstore.dto;

import lombok.Builder;

@Builder
public record AuthorRequestDTO(String name, String bio) {
}
