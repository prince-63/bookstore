package com.learn.bookstore.services;

import com.learn.bookstore.dto.AuthorRequestDTO;
import com.learn.bookstore.models.Author;

import java.util.List;

public interface AuthorService {

    Author createAuthor(AuthorRequestDTO author);

    Author getAuthorById(Long id);

    List<Author> getAuthorByName(String name);

    List<Author> getAllAuthors();

    Author updateAuthor(Long id, AuthorRequestDTO author);

    void deleteAuthor(Long id);

}
