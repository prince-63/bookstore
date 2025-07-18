package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AuthorRequestDTO(

        @NotBlank(message = "Author name is required")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        @Schema(description = "Full name of the author", example = "Robert Martin")
        String name,

        @Size(max = 1000, message = "Bio must not exceed 1000 characters")
        @Schema(description = "Brief biography of the author", example = "Author of Clean Code and other popular software engineering books.")
        String bio

) {}
