package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookRequestDTO(

        @NotBlank(message = "Title is required")
        @Size(max = 200, message = "Title must not exceed 200 characters")
        @Schema(description = "Title of the book", example = "Spring Boot in Action")
        String title,

        @NotBlank(message = "Publisher is required")
        @Size(max = 150, message = "Publisher name must not exceed 150 characters")
        @Schema(description = "Publisher name", example = "Manning Publications")
        String publisher,

        @NotNull(message = "Publication date is required")
        @PastOrPresent(message = "Publication date must be today or earlier")
        @Schema(description = "Date of publication", example = "2023-07-15")
        LocalDate publicationDate,

        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        @Schema(description = "Short description or summary of the book", example = "A comprehensive guide to Spring Boot for Java developers.")
        String description,

        @NotNull(message = "Category ID is required")
        @Schema(description = "ID of the book's category", example = "3")
        Long categoryId,

        @NotNull(message = "Author ID is required")
        @Schema(description = "ID of the author", example = "5")
        Long authorId

) {}
