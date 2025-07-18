package com.learn.bookstore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Builder
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents a book with its metadata, author, and category.")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the book", example = "101")
    private Long id;

    @Schema(description = "Title of the book", example = "The Alchemist")
    @NotBlank(message = "Title is required")
    private String title;

    @Schema(description = "Publisher of the book", example = "HarperCollins")
    @NotBlank(message = "Publisher is required")
    private String publisher;

    @Schema(description = "Publication date of the book", example = "2023-08-15")
    @Past(message = "Publisher date must be in past.")
    private LocalDate publicationDate;

    @Column(length = 1000)
    @Schema(description = "Brief description of the book", example = "A philosophical novel about following your dreams.")
    @NotBlank(message = "Book description required")
    @Size(max = 1000, message = "Book description must be under 1000 character")
    private String description;

    @Schema(description = "URL of the book's cover image", example = "https://res.cloudinary.com/.../cover.jpg")
    private String coverImageUrl;

    @Schema(description = "Public ID of the uploaded cover image", example = "bookstore/covers/alchemist")
    private String coverImagePublicId;

    @Schema(description = "URL to download the book file (e.g., PDF)", example = "https://res.cloudinary.com/.../book.pdf")
    private String bookFileUrl;

    @Schema(description = "Public ID of the uploaded book file", example = "bookstore/files/alchemist-pdf")
    private String bookFileUrlPublicId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = true)
    @JsonBackReference
    @Schema(description = "Category to which this book belongs")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    @Schema(description = "Author of the book")
    private Author author;

}

