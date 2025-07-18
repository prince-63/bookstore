package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CategoryRequestDTO(

        @NotBlank(message = "Category name is required")
        @Size(max = 100, message = "Category name must not exceed 100 characters")
        @Schema(description = "Name of the category", example = "Technology")
        String name,

        @Size(max = 255, message = "Description must not exceed 255 characters")
        @Schema(description = "Optional description of the category", example = "Events related to tech and innovation.")
        String description

) {}
