package com.learn.bookstore.services;


import com.learn.bookstore.models.Book;
import org.springframework.web.multipart.MultipartFile;

public interface PresignedUrlGeneratorService {

    Book uploadCoverImageFile(MultipartFile coverImage, Long bookId);

    Book uploadFile(MultipartFile file, Long bookId);

}