package com.learn.bookstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Standard API response wrapper")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {

    @Schema(description = "Descriptive response message", example = "Data fetched successfully")
    private String message;

    @Schema(description = "Indicates if the request was successful", example = "true")
    private boolean success;

    @Schema(description = "The actual response data")
    private T data;
}
