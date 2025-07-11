package com.learn.bookstore.services;

import com.learn.bookstore.dto.book.CategoryRequestDTO;
import com.learn.bookstore.models.book.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryRequestDTO category);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category updateCategory(Long id, CategoryRequestDTO category);

    void deleteCategory(Long id);

}
