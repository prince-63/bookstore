package com.learn.bookstore.controllers;

import com.learn.bookstore.constants.ApplicationConstants;
import com.learn.bookstore.constants.UserEndPointsConstants;
import com.learn.bookstore.dto.*;
import com.learn.bookstore.mappers.UserResponseMapper;
import com.learn.bookstore.models.User;
import com.learn.bookstore.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Tag(
        name = "User Management APIs",
        description = "Endpoints for user registration, login, profile update, and admin operations"
)
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final Environment environment;

    @Operation(summary = "Register a new user", description = "Creates a new user account with default USER role.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping(UserEndPointsConstants.REGISTER_USER)
    public ResponseEntity<ResponseDTO<UserResponseDTO>> registerUser(
            @RequestBody @Valid RegisterUserRequestDTO registerUserRequestDTO
    ) {
        User user = userService.register(registerUserRequestDTO);
        var response = buildResponse("Registration successful.", user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Register a new admin", description = "Creates a new admin account with elevated privileges.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Admin registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping(UserEndPointsConstants.REGISTER_ADMIN)
    public ResponseEntity<ResponseDTO<UserResponseDTO>> registerAdmin(
            @RequestBody @Valid RegisterUserRequestDTO registerUserRequestDTO
    ) {
        User user = userService.registerAdmin(registerUserRequestDTO);
        var response = buildResponse("Admin Registration successful.", user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Login user", description = "Authenticates a user and returns a JWT token in the Authorization header.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful. JWT token returned in header"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping(UserEndPointsConstants.LOGIN)
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO loginRequestDTO
    ) {
        String jwt;
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequestDTO.username(), loginRequestDTO.password());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        if (null != authenticationResponse && authenticationResponse.isAuthenticated()) {
            if (environment != null) {
                String secret = environment.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                jwt = Jwts.builder()
                        .issuer("bookstore")
                        .subject("JWT Token")
                        .claim("username", authenticationResponse.getName())
                        .claim("authorities", authenticationResponse.getAuthorities()
                                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new Date())
                        .expiration(new Date((new Date()).getTime() + 30000000))
                        .signWith(secretKey).compact();
                return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER, jwt).body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
            }
        } else {
            throw new BadCredentialsException("Username or password is incorrect.");
        }
        return null;
    };

    @Operation(summary = "Get user by ID", description = "Retrieves user details using user ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User data retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(UserEndPointsConstants.GET_USER_BY_ID)
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getUserById(
            @PathVariable Long id
    ) {
        User user = userService.getUserById(id);
        var response = buildResponse("User retrieved successfully.", user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get current user", description = "Fetches the currently authenticated user's details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User data retrieved"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(UserEndPointsConstants.GET_CURR_USER)
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getCurrUser(
            Authentication authentication
    ) {
        User user = userService.findByEmail(authentication.getName());
        var response = buildResponse("User retrieved successfully.", user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get all users", description = "Returns a list of all users in the system (admin only).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All users retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied for non-admins"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping(UserEndPointsConstants.GET_ALL_USERS)
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        ResponseDTO<List<UserResponseDTO>> response = new ResponseDTO<>();
        response.setData(users.stream().map(UserResponseMapper::toDTO).toList());
        response.setSuccess(true);
        response.setMessage("All users retrieved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Add or update phone number", description = "Allows user to add or update their phone number.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Phone number updated"),
            @ApiResponse(responseCode = "400", description = "Invalid phone number format"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PatchMapping(UserEndPointsConstants.ADD_PHONE_NUMBER)
    public ResponseEntity<ResponseDTO<UserResponseDTO>> addPhoneNumber(
            Authentication authentication, @RequestBody @Valid UserPhoneNumberUpdateRequestDTO phoneNumber
    ) {
        User user = userService.addPhoneNumber(authentication.getName(), phoneNumber.phoneNumber());
        var response = buildResponse("Phone number update successfully.", user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Update user profile", description = "Updates the authenticated user's profile information.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PatchMapping(UserEndPointsConstants.UPDATE_USER_ADMIN)
    public ResponseEntity<ResponseDTO<UserResponseDTO>> updateUser(
            Authentication authentication, @RequestBody @Valid UserUpdateRequestDTO user
    ) {
        User updatedUser = userService.updateUser(authentication.getName(), user);
        var response = buildResponse("User updated successfully.", updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete user", description = "Deletes the currently authenticated user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping(UserEndPointsConstants.DELETE_USER_ADMIN)
    public ResponseEntity<ResponseDTO<String>> deleteUser(
            Authentication authentication
    ) {
        userService.deleteUser(authentication.getName());
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("User deleted successfully.");
        response.setData(String.format("User deleted successfully: %s", authentication.getName()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private ResponseDTO<UserResponseDTO> buildResponse(String message, User user) {
        return new ResponseDTO<>(message, true, UserResponseMapper.toDTO(user));
    }
}
