package com.learn.bookstore.controllers;

import com.learn.bookstore.dto.BookRequestDTO;
import com.learn.bookstore.dto.BookResponseDTO;
import com.learn.bookstore.dto.ResponseDTO;
import com.learn.bookstore.mappers.BookMapper;
import com.learn.bookstore.models.Book;
import com.learn.bookstore.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<BookResponseDTO>> createNewBook(@RequestBody BookRequestDTO requestDTO) {
        Book book = bookService.createBook(requestDTO);
        ResponseDTO<BookResponseDTO> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Book created successfully");
        response.setData(BookMapper.toDTO(book));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO<BookResponseDTO>> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        ResponseDTO<BookResponseDTO> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Book found");
        response.setData(BookMapper.toDTO(book));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ResponseDTO<List<BookResponseDTO>>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return getResponseDTOResponseEntity(books);
    }

    @GetMapping("/get/category/{categoryId}")
    public ResponseEntity<ResponseDTO<List<BookResponseDTO>>> getBooksByCategory(@PathVariable Long categoryId) {
        List<Book> books = bookService.getBooksByCategory(categoryId);
        return getResponseDTOResponseEntity(books);
    }

    @GetMapping("/get/author/{authorId}")
    public ResponseEntity<ResponseDTO<List<BookResponseDTO>>> getBooksByAuthor(@PathVariable Long authorId) {
        List<Book> books = bookService.getBooksByAuthor(authorId);
        return getResponseDTOResponseEntity(books);
    }

    private ResponseEntity<ResponseDTO<List<BookResponseDTO>>> getResponseDTOResponseEntity(List<Book> books) {
        ResponseDTO<List<BookResponseDTO>> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Books found");
        response.setData(books.stream().map(BookMapper::toDTO).collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseDTO<BookResponseDTO>> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO requestDTO) {
        Book book = bookService.updateBook(id, requestDTO);
        ResponseDTO<BookResponseDTO> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Book updated successfully");
        response.setData(BookMapper.toDTO(book));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Book deleted successfully");
        response.setData(String.format("Book deleted successfully with id: %d", id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
