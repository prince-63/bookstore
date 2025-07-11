package com.learn.bookstore.models.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learn.bookstore.models.util.BaseEntity;
import com.learn.bookstore.models.book.Book;
import com.learn.bookstore.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="book_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Book book;

    private int quantity;
}

