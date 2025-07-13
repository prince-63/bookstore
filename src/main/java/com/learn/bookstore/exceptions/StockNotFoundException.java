package com.learn.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String resourceName, String fieldName, String fieldValue) {
      super(String.format("%s stock not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
