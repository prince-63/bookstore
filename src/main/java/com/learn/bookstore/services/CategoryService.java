package com.learn.bookstore.services;

import com.learn.bookstore.models.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getAllCategories();

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);

}
