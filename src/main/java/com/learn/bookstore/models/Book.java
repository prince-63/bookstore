package com.learn.bookstore.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String isbn;

    private String publisher;

    private double price;

    private int stock;

    private LocalDate publicationDate;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

    @OneToMany(mappedBy = "book")
    private List<Wishlist> wishlists;

    @OneToMany(mappedBy = "book")
    private List<Discount> discounts;

    @OneToMany(mappedBy = "book")
    private List<InventoryLog> inventoryLogs;
}

