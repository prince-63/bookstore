package com.learn.bookstore.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "users")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Schema(description = "Represents a user of the bookstore application.")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the user", example = "1001")
    private Long id;

    @Schema(description = "Full name of the user", example = "Alice Johnson")
    private String name;

    @Column(unique = true, nullable = false)
    @Schema(description = "Unique email address of the user", example = "alice@example.com")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(description = "User's password (write-only)", example = "securePassword123")
    private String password;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Role of the user", example = "USER", allowableValues = {"USER", "ADMIN"})
    private Role role;

    @Schema(description = "Phone number of the user", example = "+91-9876543210")
    private String phone;

}
