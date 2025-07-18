package com.learn.bookstore.controllers;

import com.learn.bookstore.constants.BookEndPointsConstants;
import com.learn.bookstore.dto.BookRequestDTO;
import com.learn.bookstore.dto.BookResponseDTO;
import com.learn.bookstore.dto.ErrorResponseDTO;
import com.learn.bookstore.dto.ResponseDTO;
import com.learn.bookstore.mappers.BookMapper;
import com.learn.bookstore.models.Book;
import com.learn.bookstore.services.BookService;
import com.learn.bookstore.services.PresignedUrlGeneratorService;
import com.learn.bookstore.utils.BookFileUtil;
import com.learn.bookstore.utils.CoverImageFileUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Book Management APIs", description = "Operations for managing books, including CRUD, cover image, and file uploads")
@RestController
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final PresignedUrlGeneratorService presignedUrlGeneratorService;

    @Operation(summary = "Create a new book", description = "Creates a new book entry with provided details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping(BookEndPointsConstants.CREATE_NEW_BOOK)
    public ResponseEntity<ResponseDTO<BookResponseDTO>> createNewBook(
            @RequestBody @Valid BookRequestDTO requestDTO
    ) {
        Book book = bookService.createBook(requestDTO);
        var response = buildResponse("Book created successfully", book);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Upload cover image", description = "Uploads a cover image file for a book by bookId.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cover image uploaded successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid image file or unsupported format"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PatchMapping(BookEndPointsConstants.UPLOAD_COVER_BOOK_IMAGE)
    public ResponseEntity<ResponseDTO<BookResponseDTO>> uploadCoverImage(
            @RequestParam("imageFile") MultipartFile imageFile,
            @PathVariable Long bookId
    ) {
        CoverImageFileUtil.assertAllowed(imageFile, CoverImageFileUtil.IMAGE_PATTERN);
        Book updatedBook = presignedUrlGeneratorService.uploadCoverImageFile(imageFile, bookId);
        var response =  buildResponse("Cover image upload successfully", updatedBook);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Upload book file", description = "Uploads a downloadable book file (e.g., PDF) by bookId.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book file uploaded successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid file type or content"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PatchMapping(BookEndPointsConstants.UPLOAD_BOOK_FILE)
    public ResponseEntity<ResponseDTO<BookResponseDTO>> uploadBookFile(
            @RequestParam("bookFile") MultipartFile imageFile,
            @PathVariable Long bookId
    ) {
        BookFileUtil.assertAllowed(imageFile, BookFileUtil.FILE_PATTERN);
        Book updatedBook = presignedUrlGeneratorService.uploadFile(imageFile, bookId);
        var response =  buildResponse("Book file upload successfully", updatedBook);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get book by ID", description = "Retrieves the book details by its unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(BookEndPointsConstants.GET_BOOK_BY_ID)
    public ResponseEntity<ResponseDTO<BookResponseDTO>> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        var response = buildResponse("Book retrieved successfully", book);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get all books", description = "Returns a list of all books available in the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(BookEndPointsConstants.GET_ALL_BOOKS)
    public ResponseEntity<ResponseDTO<List<BookResponseDTO>>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        var response = buildResponseList(books);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get books by category", description = "Retrieves all books belonging to the given category ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(BookEndPointsConstants.GET_BOOK_BY_CATEGORY_ID)
    public ResponseEntity<ResponseDTO<List<BookResponseDTO>>> getBooksByCategory(@PathVariable Long categoryId) {
        List<Book> books = bookService.getBooksByCategory(categoryId);
        var response = buildResponseList(books);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get books by author", description = "Retrieves all books written by the given author ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(BookEndPointsConstants.GET_BOOK_BY_AUTHOR_ID)
    public ResponseEntity<ResponseDTO<List<BookResponseDTO>>> getBooksByAuthor(@PathVariable Long authorId) {
        List<Book> books = bookService.getBooksByAuthor(authorId);
        var response = buildResponseList(books);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Update book by ID", description = "Updates book details using the provided book ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid update data or ID"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PatchMapping(BookEndPointsConstants.UPDATE_BOOK_BY_ID)
    public ResponseEntity<ResponseDTO<BookResponseDTO>> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO requestDTO) {
        Book book = bookService.updateBook(id, requestDTO);
        var response = buildResponse("Book updated successfully", book);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete book by ID", description = "Deletes a book record using the book ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping(BookEndPointsConstants.DELETE_BOOK_BY_ID)
    public ResponseEntity<ResponseDTO<String>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Book deleted successfully");
        response.setData(String.format("Book deleted successfully with id: %d", id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private ResponseDTO<List<BookResponseDTO>> buildResponseList(List<Book> books) {
        return new ResponseDTO<>("Books Found!", true, books.stream().map(BookMapper::toDTO).toList());
    }

    private ResponseDTO<BookResponseDTO> buildResponse(String message, Book book) {
        return new ResponseDTO<>(message, true, BookMapper.toDTO(book));
    }
}
