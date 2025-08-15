package com.learn.bookstore.services.impl;

import com.learn.bookstore.dto.CategoryRequestDTO;
import com.learn.bookstore.exceptions.ResourceNotFoundException;
import com.learn.bookstore.mappers.CategoryMapper;
import com.learn.bookstore.models.Category;
import com.learn.bookstore.repositories.CategoryRepository;
import com.learn.bookstore.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryRequestDTO requestDTO) {
        Category category = CategoryMapper.toModel(requestDTO);
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return getById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, CategoryRequestDTO requestDTO) {
        Category oldCategory = getById(id);

        Category category = CategoryMapper.toModel(requestDTO);
        oldCategory.setName(category.getName() != null ? category.getName() : oldCategory.getName());
        oldCategory.setDescription(category.getDescription() != null ? category.getDescription() : oldCategory.getDescription());
        return categoryRepository.save(oldCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category oldCategory = getById(id);
        categoryRepository.delete(oldCategory);
    }

    private Category getById(Long id) throws ResourceNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id.toString()));
    }
}
