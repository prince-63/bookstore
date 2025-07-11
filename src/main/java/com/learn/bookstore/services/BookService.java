package com.learn.bookstore.services;

import com.learn.bookstore.models.book.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

    Book getBookById(Long id);

    List<Book> getAllBooks();

    List<Book> searchBooksByTitle(String title);

    List<Book> getBooksByCategory(Long categoryId);

    List<Book> getBooksByAuthor(Long authorId);

}

