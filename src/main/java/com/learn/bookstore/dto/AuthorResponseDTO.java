package com.learn.bookstore.dto;

import lombok.Builder;

@Builder
public record AuthorResponseDTO(Long id, String name, String bio){
}
