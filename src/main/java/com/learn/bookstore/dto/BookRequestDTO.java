package com.learn.bookstore.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookRequestDTO(String title, String publisher, double price, int stock, LocalDate publicationDate, String description, Long categoryId, Long authorId) {
}
