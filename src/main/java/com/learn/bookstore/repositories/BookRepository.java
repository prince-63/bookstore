package com.learn.bookstore.repositories;

import com.learn.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCategoryId(Long categoryId);

    List<Book> findByAuthorId(Long authorId);

    List<Book> findAllByTitleIgnoreCase(String title);

}

