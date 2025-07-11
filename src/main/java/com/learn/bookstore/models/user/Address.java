package com.learn.bookstore.models.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learn.bookstore.models.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = true)
    @JsonBackReference
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


