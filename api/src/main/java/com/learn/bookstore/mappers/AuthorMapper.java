package com.learn.bookstore.mappers;

import com.learn.bookstore.dto.AuthorRequestDTO;
import com.learn.bookstore.dto.AuthorResponseDTO;
import com.learn.bookstore.models.Author;

public class AuthorMapper {

    public static Author toModel(AuthorRequestDTO author) {
        return Author.builder()
                .name(author.name())
                .bio(author.bio())
                .build();
    }

    public static AuthorResponseDTO toDTO(Author author) {
        return AuthorResponseDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .bio(author.getBio())
                .build();
    }

}
