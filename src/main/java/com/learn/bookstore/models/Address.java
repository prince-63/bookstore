package com.learn.bookstore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Schema(description = "Represents a user's address details, such as city, country, and whether it's default.")
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the address", example = "101")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = true)
    @JsonBackReference
    @Schema(description = "User associated with this address")
    private User user;

    @Schema(description = "Primary address line", example = "221B Baker Street")
    private String line1;

    @Schema(description = "Secondary address line (optional)", example = "Apt. 5")
    private String line2;

    @Schema(description = "City name", example = "London")
    private String city;

    @Schema(description = "State or province", example = "Greater London")
    private String state;

    @Schema(description = "Postal code or ZIP", example = "NW1 6XE")
    private String postalCode;

    @Schema(description = "Country", example = "United Kingdom")
    private String country;

    @Schema(description = "Flag indicating if this is the default address", example = "true")
    private boolean isDefault;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Type of address", example = "SHIPPING", allowableValues = {"PERMANENT"})
    private AddressType type;
}


