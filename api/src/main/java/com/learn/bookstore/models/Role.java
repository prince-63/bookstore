package com.learn.bookstore.models;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Defines roles assigned to users for access control.")
public enum Role {

    @Schema(description = "Standard user role with limited access")
    USER,

    @Schema(description = "Administrator role with elevated permissions")
    ADMIN
}
