package com.learn.bookstore.controllers;

import com.learn.bookstore.dto.ResponseDTO;
import com.learn.bookstore.dto.book.CategoryRequestDTO;
import com.learn.bookstore.dto.book.CategoryResponseDTO;
import com.learn.bookstore.mappers.CategoryMapper;
import com.learn.bookstore.models.book.Category;
import com.learn.bookstore.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService  categoryService;

    @PostMapping("/category")
    public ResponseEntity<ResponseDTO<CategoryResponseDTO>> createCategory(@RequestBody CategoryRequestDTO requestDTO) {
        Category createdCategory = categoryService.createCategory(requestDTO);
        ResponseDTO<CategoryResponseDTO> response = new ResponseDTO<>();
        response.setData(CategoryMapper.toDTO(createdCategory));
        response.setSuccess(true);
        response.setMessage("Category created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/category/get/{id}")
    public ResponseEntity<ResponseDTO<CategoryResponseDTO>> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        ResponseDTO<CategoryResponseDTO> response = new ResponseDTO<>();
        response.setData(CategoryMapper.toDTO(category));
        response.setSuccess(true);
        response.setMessage("Category retrieved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/category/get")
    public ResponseEntity<ResponseDTO<List<CategoryResponseDTO>>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        ResponseDTO<List<CategoryResponseDTO>> response = new ResponseDTO<>();
        response.setData(categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList()));
        response.setSuccess(true);
        response.setMessage("All categories retrieved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/category/{id}")
    public ResponseEntity<ResponseDTO<CategoryResponseDTO>> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        ResponseDTO<CategoryResponseDTO> response = new ResponseDTO<>();
        response.setData(CategoryMapper.toDTO(updatedCategory));
        response.setSuccess(true);
        response.setMessage("Category updated successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<ResponseDTO<?>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        ResponseDTO<CategoryResponseDTO> response = new ResponseDTO<>();
        response.setData(null);
        response.setSuccess(true);
        response.setMessage("Category deleted successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
