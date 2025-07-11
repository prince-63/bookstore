package com.learn.bookstore.models.book;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learn.bookstore.models.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String bio;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Book.class)
    @JsonManagedReference
    private Set<Book> books;
}

