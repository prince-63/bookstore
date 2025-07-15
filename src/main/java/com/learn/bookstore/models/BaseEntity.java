package com.learn.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Base entity containing common auditing fields for all models.")
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    @JsonIgnore
    @Schema(description = "Timestamp when the entity was created", example = "2024-05-01T10:15:30")
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    @JsonIgnore
    @Schema(description = "Username or system who created the entity", example = "admin")
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    @JsonIgnore
    @Schema(description = "Timestamp when the entity was last updated", example = "2024-06-10T14:42:00")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    @JsonIgnore
    @Schema(description = "Username or system who last modified the entity", example = "admin")
    private String updatedBy;
}
