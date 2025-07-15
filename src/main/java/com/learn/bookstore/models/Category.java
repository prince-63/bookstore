package com.learn.bookstore.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents a book category such as Fiction, Science, or Biography.")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the category", example = "1")
    private Long id;

    @Schema(description = "Name of the category", example = "Science Fiction")
    private String name;

    @Column(length = 500)
    @Schema(description = "Detailed description of the category", example = "Books that are based on speculative scientific concepts.")
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Book.class)
    @Schema(description = "Set of books that belong to this category")
    private Set<Book> books;
}