package com.learn.bookstore.controllers;

import com.learn.bookstore.dto.AuthorRequestDTO;
import com.learn.bookstore.dto.AuthorResponseDTO;
import com.learn.bookstore.dto.ResponseDTO;
import com.learn.bookstore.mappers.AuthorMapper;
import com.learn.bookstore.models.Author;
import com.learn.bookstore.services.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/author")
    public ResponseEntity<ResponseDTO<AuthorResponseDTO>>createAuthor(@RequestBody AuthorRequestDTO author) {
        Author savedAuthor = authorService.createAuthor(author);
        ResponseDTO<AuthorResponseDTO> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Author created successfully");
        response.setData(AuthorMapper.toDTO(savedAuthor));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<ResponseDTO<AuthorResponseDTO>> getAuthor(@PathVariable("id") Long id) {
        Author author = authorService.getAuthorById(id);
        if (author.getId() > 0) {
            AuthorResponseDTO authorResponseDTO = AuthorMapper.toDTO(author);
            ResponseDTO<AuthorResponseDTO> response = new ResponseDTO<>();
            response.setSuccess(true);
            response.setMessage("Author found successfully");
            response.setData(authorResponseDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/author/get/{name}")
    public ResponseEntity<ResponseDTO<List<AuthorResponseDTO>>> getAuthorByName(@PathVariable("name") String name) {
        List<Author> authors = authorService.getAuthorByName(name);
        return getResponseDTOResponseEntity(authors);
    }

    @GetMapping("/author/get/all")
    public ResponseEntity<ResponseDTO<List<AuthorResponseDTO>>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return getResponseDTOResponseEntity(authors);
    }

    @PatchMapping("/author/{id}")
    public ResponseEntity<ResponseDTO<AuthorResponseDTO>> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorRequestDTO author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);
        ResponseDTO<AuthorResponseDTO> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Author updated successfully");
        response.setData(AuthorMapper.toDTO(updatedAuthor));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Author deleted successfully");
        response.setData(String.format("Author deleted successful with id: %d", id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private ResponseEntity<ResponseDTO<List<AuthorResponseDTO>>> getResponseDTOResponseEntity(List<Author> authors) {
        ResponseDTO<List<AuthorResponseDTO>> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Authors found");
        response.setData(authors.stream().map(AuthorMapper::toDTO).collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
