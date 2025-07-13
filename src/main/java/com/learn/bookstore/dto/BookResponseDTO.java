package com.learn.bookstore.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookResponseDTO(Long id, String title, String publisher, double price, int stock, LocalDate publicationDate, String description, String coverImageUrl, String coverImagePublicId, String bookFileUrl, String bookFileUrlPublicId, Long categoryId, Long authorId) {
}
