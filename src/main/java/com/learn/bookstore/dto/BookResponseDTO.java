package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookResponseDTO(

        @Schema(description = "Unique identifier of the book", example = "1")
        Long id,

        @Schema(description = "Title of the book", example = "Clean Code")
        String title,

        @Schema(description = "Name of the publisher", example = "Prentice Hall")
        String publisher,

        @Schema(description = "Date when the book was published", example = "2008-08-11")
        LocalDate publicationDate,

        @Schema(description = "Brief description or summary of the book", example = "A handbook of agile software craftsmanship.")
        String description,

        @Schema(description = "URL to the book's cover image", example = "https://res.cloudinary.com/your-app/image/upload/v123456789/book-cover.jpg")
        String coverImageUrl,

        @Schema(description = "Cloudinary public ID of the book's cover image", example = "book-cover-abc123")
        String coverImagePublicId,

        @Schema(description = "URL to the uploaded book file (e.g., PDF)", example = "https://res.cloudinary.com/your-app/docs/book.pdf")
        String bookFileUrl,

        @Schema(description = "Cloudinary public ID of the uploaded book file", example = "book-file-xyz456")
        String bookFileUrlPublicId,

        @Schema(description = "ID of the category this book belongs to", example = "2")
        Long categoryId,

        @Schema(description = "ID of the author who wrote the book", example = "5")
        Long authorId

) {}
