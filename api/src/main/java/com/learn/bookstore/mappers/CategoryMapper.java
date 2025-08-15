package com.learn.bookstore.mappers;

import com.learn.bookstore.dto.CategoryRequestDTO;
import com.learn.bookstore.dto.CategoryResponseDTO;
import com.learn.bookstore.models.Category;

public class CategoryMapper {

    public static CategoryResponseDTO toDTO(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public static Category toModel(CategoryRequestDTO categoryRequestDTO) {
        return Category.builder()
                .name(categoryRequestDTO.name())
                .description(categoryRequestDTO.description())
                .build();
    }

}
