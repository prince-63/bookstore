package com.learn.bookstore.services.impl;

import com.learn.bookstore.dto.BookRequestDTO;
import com.learn.bookstore.exceptions.ResourceNotFoundException;
import com.learn.bookstore.mappers.BookMapper;
import com.learn.bookstore.models.Author;
import com.learn.bookstore.models.Book;
import com.learn.bookstore.models.Category;
import com.learn.bookstore.repositories.BookRepository;
import com.learn.bookstore.services.AuthorService;
import com.learn.bookstore.services.BookService;
import com.learn.bookstore.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    @Override
    public Book createBook(BookRequestDTO requestDTO) {
        Category category = categoryService.getCategoryById(requestDTO.categoryId());
        Author author = authorService.getAuthorById(requestDTO.authorId());
        Book book = BookMapper.toModel(requestDTO);
        book.setCategory(category);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) throws ResourceNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id.toString()));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findAllByTitleIgnoreCase(title);
    }

    @Override
    public List<Book> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @Override
    public Book updateBook(Long id, BookRequestDTO updatedBook) throws ResourceNotFoundException {
        Book dbBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id.toString()));
        Category category = categoryService.getCategoryById(updatedBook.categoryId());
        Author author = authorService.getAuthorById(updatedBook.authorId());
        dbBook.setAuthor(author);
        dbBook.setCategory(category);
        dbBook.setTitle(updatedBook.title() != null  ? updatedBook.title() : dbBook.getTitle());
        dbBook.setDescription(updatedBook.description() != null ? updatedBook.description() : dbBook.getDescription());
        dbBook.setPublicationDate(updatedBook.publicationDate() != null ? updatedBook.publicationDate() : dbBook.getPublicationDate());
        dbBook.setPublisher(updatedBook.publisher() != null ? updatedBook.publisher() : dbBook.getPublisher());
        return bookRepository.save(dbBook);
    }

    @Override
    public void deleteBook(Long id) throws ResourceNotFoundException {
        Book dbBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id.toString()));
        bookRepository.deleteById(dbBook.getId());
    }
}
