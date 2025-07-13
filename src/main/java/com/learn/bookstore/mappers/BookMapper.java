package com.learn.bookstore.mappers;

import com.learn.bookstore.dto.BookRequestDTO;
import com.learn.bookstore.dto.BookResponseDTO;
import com.learn.bookstore.models.Book;

public class BookMapper {

    public static Book toModel(BookRequestDTO requestDTO) {
        return Book.builder()
                .title(requestDTO.title())
                .description(requestDTO.description())
                .price(requestDTO.price())
                .stock(requestDTO.stock())
                .publicationDate(requestDTO.publicationDate())
                .publisher(requestDTO.publisher())
                .build();
    }

    public static BookResponseDTO toDTO(Book book) {
        return BookResponseDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .price(book.getPrice())
                .stock(book.getStock())
                .publicationDate(book.getPublicationDate())
                .publisher(book.getPublisher())
                .categoryId(book.getCategory().getId())
                .authorId(book.getAuthor().getId())
                .build();
    }
}
