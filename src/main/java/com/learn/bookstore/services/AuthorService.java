package com.learn.bookstore.services;

import com.learn.bookstore.models.book.Author;

import java.util.List;

public interface AuthorService {

    Author createAuthor(Author author);

    Author updateAuthor(Long id, Author author);

    void deleteAuthor(Long id);

    List<Author> getAllAuthors();

}
