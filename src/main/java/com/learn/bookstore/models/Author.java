package com.learn.bookstore.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents a book author with name, biography, and associated books.")
public class Author extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the author", example = "10")
    private Long id;

    @Schema(description = "Full name of the author", example = "J.K. Rowling")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Short biography of the author", example = "British author, best known for the Harry Potter series.")
    @NotBlank(message = "Bio is required")
    @Size(max=500, message = "Bio must be under 500 character")
    private String bio;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Book.class)
    @JsonManagedReference
    @Schema(description = "Set of books written by the author")
    private Set<Book> books;
}

