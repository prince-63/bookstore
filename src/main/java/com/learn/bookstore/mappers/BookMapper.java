package com.learn.bookstore.mappers;

import com.learn.bookstore.dto.BookRequestDTO;
import com.learn.bookstore.dto.BookResponseDTO;
import com.learn.bookstore.models.Book;

public class BookMapper {

    public static Book toModel(BookRequestDTO requestDTO) {
        return Book.builder()
                .title(requestDTO.title())
                .description(requestDTO.description())
                .publicationDate(requestDTO.publicationDate())
                .publisher(requestDTO.publisher())
                .build();
    }

    public static BookResponseDTO toDTO(Book book) {
        return BookResponseDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .bookFileUrl(book.getBookFileUrl())
                .coverImageUrl(book.getCoverImageUrl())
                .coverImagePublicId(book.getCoverImagePublicId())
                .bookFileUrlPublicId(book.getBookFileUrlPublicId())
                .publicationDate(book.getPublicationDate())
                .publisher(book.getPublisher())
                .categoryId(book.getCategory().getId())
                .authorId(book.getAuthor().getId())
                .build();
    }
}
