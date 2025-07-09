package com.learn.bookstore.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    private String carrier;

    private String trackingNumber;

    private String status;

    private LocalDateTime shippedAt;

    private LocalDateTime deliveredAt;
}

