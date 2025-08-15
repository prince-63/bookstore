package com.learn.bookstore.services;

import com.learn.bookstore.dto.BookRequestDTO;
import com.learn.bookstore.models.Book;

import java.util.List;

public interface BookService {

    Book createBook(BookRequestDTO book);

    Book getBookById(Long id);

    List<Book> getAllBooks();

    List<Book> searchBooksByTitle(String title);

    List<Book> getBooksByCategory(Long categoryId);

    List<Book> getBooksByAuthor(Long authorId);

    Book updateBook(Long id, BookRequestDTO book);

    void deleteBook(Long id);

}

