package com.learn.bookstore.services.impl;

import com.cloudinary.Cloudinary;
import com.learn.bookstore.exceptions.ResourceNotFoundException;
import com.learn.bookstore.models.Book;
import com.learn.bookstore.repositories.BookRepository;
import com.learn.bookstore.services.PresignedUrlGeneratorService;
import com.learn.bookstore.utils.BookFileUtil;
import com.learn.bookstore.utils.CoverImageFileUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class PresignedUrlGeneratorServiceImpl implements PresignedUrlGeneratorService {

    private final Cloudinary cloudinary;
    private final BookRepository bookRepository;

    @Override
    public Book uploadCoverImageFile(MultipartFile coverImage, Long bookId) {
        try {
            final String fileName = CoverImageFileUtil.getFileName(FilenameUtils.getBaseName(coverImage.getOriginalFilename()));
            final File uploadedFile = convertMultiPartToFile(coverImage);
            Map<String, Object> uploadOptions = Map.of("public_id", "book/cover/" + fileName);
            Map result = cloudinary.uploader().upload(uploadedFile, uploadOptions);

            Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId.toString()));
            book.setCoverImageUrl((String) result.get("url"));
            book.setCoverImagePublicId((String) result.get("public_id"));
            Book response = bookRepository.save(book);

            deleteTempFile(uploadedFile);

            return response;
        } catch (IOException e) {
            throw new RuntimeException("File upload failed due to IO error: " + e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during file upload: " + e);
        }
    }

    @Override
    public Book uploadFile(MultipartFile file, Long bookId) {
        try {
            final String fileName = BookFileUtil.getFileName(FilenameUtils.getBaseName(file.getOriginalFilename()));
            final File uploadedFile = convertMultiPartToFile(file);
            Map<String, Object> uploadOptions = Map.of("public_id", "book/" + fileName);
            Map result = cloudinary.uploader().upload(uploadedFile, uploadOptions);

            Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId.toString()));
            book.setBookFileUrl((String) result.get("url"));
            book.setBookFileUrlPublicId((String) result.get("public_id"));
            Book response = bookRepository.save(book);

            deleteTempFile(uploadedFile);

            return response;
        } catch (IOException e) {
            throw new RuntimeException("File upload failed due to IO error: " + e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during file upload: " + e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        String prefix = "upload_";
        String suffix = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        File tempFile = Files.createTempFile(prefix, suffix).toFile();

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }

        return tempFile;
    }

    private void deleteTempFile(File uploadedFile) {
        if (uploadedFile.delete()) {
            log.info("Temp file deleted successfully.");
        } else {
            log.warn("Failed to delete temp file: {}", uploadedFile.getAbsolutePath());
        }
    }

}
