package com.learn.bookstore.models;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Type of address associated with a user.")
public enum AddressType {

    @Schema(description = "Permanent address of the user")
    PERMANENT
}
