package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AuthorResponseDTO(

        @Schema(description = "Unique identifier of the author", example = "1")
        Long id,

        @Schema(description = "Full name of the author", example = "Robert C. Martin")
        String name,

        @Schema(description = "Short biography of the author", example = "Author of Clean Code and advocate of software craftsmanship.")
        String bio

) {}
