package com.learn.bookstore.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDTO(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {

}

//private  String apiPath;
//
//@Schema(
//        description = "Error code representing the error happened"
//)
//private HttpStatus errorCode;
//
//@Schema(
//        description = "Error message representing the error happened"
//)
//private  String errorMessage;
//
//@Schema(
//        description = "Time representing when the error happened"
//)
//private LocalDateTime errorTime;