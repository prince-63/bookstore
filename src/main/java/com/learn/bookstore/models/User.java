package com.learn.bookstore.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Name is required")
    private String name;

    @Column(unique = true, nullable = false)
    @Schema(description = "Unique email address of the user", example = "alice@example.com")
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(description = "User's password (write-only)", example = "securePassword123")
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Role of the user", example = "USER", allowableValues = {"USER", "ADMIN"})
    private Role role;

    @Schema(description = "Phone number of the user", example = "+91-9876543210")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be a valid 10-digit Indian mobile number"
    )
    private String phone;

}
