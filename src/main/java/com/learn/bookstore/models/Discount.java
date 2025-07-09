package com.learn.bookstore.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private double percentage;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active;

    @ManyToOne
    private Book book;
}

