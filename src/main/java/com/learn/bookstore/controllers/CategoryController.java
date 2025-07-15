package com.learn.bookstore.controllers;

import com.learn.bookstore.constants.BookEndPointsConstants;
import com.learn.bookstore.dto.CategoryRequestDTO;
import com.learn.bookstore.dto.CategoryResponseDTO;
import com.learn.bookstore.dto.ErrorResponseDTO;
import com.learn.bookstore.dto.ResponseDTO;
import com.learn.bookstore.mappers.CategoryMapper;
import com.learn.bookstore.models.Category;
import com.learn.bookstore.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Tag(name = "Category Management APIs", description = "Operations for managing category, including CRUD, create, update, delete")
@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService  categoryService;

    @Operation(summary = "Create new category", description = "Creates a new category with the provided name and description.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping(BookEndPointsConstants.CREATE_CATEGORY)
    public ResponseEntity<ResponseDTO<CategoryResponseDTO>> createCategory(@RequestBody CategoryRequestDTO requestDTO) {
        Category createdCategory = categoryService.createCategory(requestDTO);
        ResponseDTO<CategoryResponseDTO> response = new ResponseDTO<>();
        response.setData(CategoryMapper.toDTO(createdCategory));
        response.setSuccess(true);
        response.setMessage("Category created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get category by ID", description = "Fetches a single category by its unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(BookEndPointsConstants.GET_CATEGORY_BY_ID)
    public ResponseEntity<ResponseDTO<CategoryResponseDTO>> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        ResponseDTO<CategoryResponseDTO> response = new ResponseDTO<>();
        response.setData(CategoryMapper.toDTO(category));
        response.setSuccess(true);
        response.setMessage("Category retrieved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get all categories", description = "Retrieves a list of all available book categories.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All categories retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(BookEndPointsConstants.GET_ALL_CATEGORIES)
    public ResponseEntity<ResponseDTO<List<CategoryResponseDTO>>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        ResponseDTO<List<CategoryResponseDTO>> response = new ResponseDTO<>();
        response.setData(categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList()));
        response.setSuccess(true);
        response.setMessage("All categories retrieved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Update category by ID", description = "Updates category details for the given ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid update request or ID"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PatchMapping(BookEndPointsConstants.UPDATE_CATEGORY_BY_ID)
    public ResponseEntity<ResponseDTO<CategoryResponseDTO>> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        ResponseDTO<CategoryResponseDTO> response = new ResponseDTO<>();
        response.setData(CategoryMapper.toDTO(updatedCategory));
        response.setSuccess(true);
        response.setMessage("Category updated successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete category by ID", description = "Deletes the category associated with the given ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping(BookEndPointsConstants.DELETE_CATEGORY_BY_ID)
    public ResponseEntity<ResponseDTO<?>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        ResponseDTO<CategoryResponseDTO> response = new ResponseDTO<>();
        response.setData(null);
        response.setSuccess(true);
        response.setMessage("Category deleted successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
