package com.learn.bookstore.services.impl;

import com.learn.bookstore.dto.AuthorRequestDTO;
import com.learn.bookstore.exceptions.ResourceNotFoundException;
import com.learn.bookstore.mappers.AuthorMapper;
import com.learn.bookstore.models.Author;
import com.learn.bookstore.repositories.AuthorRepository;
import com.learn.bookstore.services.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author createAuthor(AuthorRequestDTO author) {
        return authorRepository.save(AuthorMapper.toModel(author));
    }

    @Override
    public Author getAuthorById(Long id) throws ResourceNotFoundException {
        return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id.toString()));
    }

    @Override
    public List<Author> getAuthorByName(String name) {
        return authorRepository.findAllByNameIgnoreCase(name);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author updateAuthor(Long id, AuthorRequestDTO author) throws ResourceNotFoundException {
        Author dbAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id.toString()));
        dbAuthor.setName(author.name() != null ? author.name() : dbAuthor.getName());
        dbAuthor.setBio(author.bio() != null ? author.bio() : dbAuthor.getBio());
        return authorRepository.save(dbAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author dbAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id.toString()));
        authorRepository.deleteById(dbAuthor.getId());
    }
}
