package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CategoryResponseDTO(

        @Schema(description = "Unique identifier of the category", example = "1")
        Long id,

        @Schema(description = "Name of the category", example = "Programming")
        String name,

        @Schema(description = "Brief description of the category", example = "Books related to software development and coding practices.")
        String description

) {}
