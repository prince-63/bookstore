package com.learn.bookstore.mappers;

import com.learn.bookstore.dto.book.CategoryRequestDTO;
import com.learn.bookstore.dto.book.CategoryResponseDTO;
import com.learn.bookstore.models.book.Category;

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
