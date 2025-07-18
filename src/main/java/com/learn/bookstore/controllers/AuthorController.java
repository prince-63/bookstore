package com.learn.bookstore.controllers;

import com.learn.bookstore.constants.BookEndPointsConstants;
import com.learn.bookstore.dto.AuthorRequestDTO;
import com.learn.bookstore.dto.AuthorResponseDTO;
import com.learn.bookstore.dto.ErrorResponseDTO;
import com.learn.bookstore.dto.ResponseDTO;
import com.learn.bookstore.mappers.AuthorMapper;
import com.learn.bookstore.models.Author;
import com.learn.bookstore.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Author Management APIs",
        description = "Endpoints for creating, retrieving, updating, and deleting author data."
)
@RestController
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "Create Author", description = "Adds a new author to the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Author created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping(BookEndPointsConstants.CREATE_AUTHOR)
    public ResponseEntity<ResponseDTO<AuthorResponseDTO>>createAuthor(@RequestBody AuthorRequestDTO author) {
        Author savedAuthor = authorService.createAuthor(author);
        var response = buildResponse("Author created successfully", savedAuthor);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get Author by ID", description = "Fetches author details using the author ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(BookEndPointsConstants.GET_AUTHOR_BY_ID)
    public ResponseEntity<ResponseDTO<AuthorResponseDTO>> getAuthor(@PathVariable("id") Long id) {
        Author author = authorService.getAuthorById(id);
        if (author.getId() > 0) {
            var response =  buildResponse("Author retrieved successfully", author);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Get Authors by Name", description = "Searches and returns authors matching the given name.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authors retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(BookEndPointsConstants.GET_AUTHORS_BY_NAME)
    public ResponseEntity<ResponseDTO<List<AuthorResponseDTO>>> getAuthorByName(@PathVariable("name") String name) {
        List<Author> authors = authorService.getAuthorByName(name);
        var response = buildResponseList(authors);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get All Authors", description = "Fetches a list of all authors.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authors retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(BookEndPointsConstants.GET_ALL_AUTHORS)
    public ResponseEntity<ResponseDTO<List<AuthorResponseDTO>>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        var response = buildResponseList(authors);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Update Author", description = "Updates author details using the author ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or ID"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PatchMapping(BookEndPointsConstants.UPDATE_AUTHOR_BY_ID)
    public ResponseEntity<ResponseDTO<AuthorResponseDTO>> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorRequestDTO author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);
        var response =  buildResponse("Author updated successfully", updatedAuthor);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete Author", description = "Deletes the author identified by the given ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping(BookEndPointsConstants.DELETE_AUTHOR_BY_ID)
    public ResponseEntity<ResponseDTO<String>> deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Author deleted successfully");
        response.setData(String.format("Author deleted successful with id: %d", id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private ResponseDTO<List<AuthorResponseDTO>> buildResponseList(List<Author> authors) {
        return new ResponseDTO<>("Authors retrieved successfully", true, authors.stream().map(AuthorMapper::toDTO).toList());
    }

    private ResponseDTO<AuthorResponseDTO> buildResponse(String message, Author author) {
        return new ResponseDTO<>(message, true, AuthorMapper.toDTO(author));
    }
}
