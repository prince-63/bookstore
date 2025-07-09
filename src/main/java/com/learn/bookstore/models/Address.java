package com.learn.bookstore.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String line1;

    private String line2;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    private boolean isDefault;

    @Enumerated(EnumType.STRING)
    private AddressType type;
}


