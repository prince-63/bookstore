package com.learn.bookstore.controllers;

import com.learn.bookstore.constants.ApplicationConstants;
import com.learn.bookstore.dto.*;
import com.learn.bookstore.mappers.UserResponseMapper;
import com.learn.bookstore.models.User;
import com.learn.bookstore.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final Environment environment;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> registerUser(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        User user = userService.register(registerUserRequestDTO);
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(UserResponseMapper.toDTO(user));
        response.setSuccess(true);
        response.setMessage("Registration successful.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
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

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(UserResponseMapper.toDTO(user));
        response.setSuccess(true);
        response.setMessage("User retrieved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-curr")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getCurrUser(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(UserResponseMapper.toDTO(user));
        response.setSuccess(true);
        response.setMessage("User retrieved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        ResponseDTO<List<UserResponseDTO>> response = new ResponseDTO<>();
        response.setData(users.stream().map(UserResponseMapper::toDTO).toList());
        response.setSuccess(true);
        response.setMessage("All users retrieved successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/add-phone")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> addPhoneNumber(Authentication authentication, @RequestBody UserPhoneNumberUpdateRequestDTO phoneNumber) {
        User user = userService.addPhoneNumber(authentication.getName(), phoneNumber.phoneNumber());
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(UserResponseMapper.toDTO(user));
        response.setSuccess(true);
        response.setMessage("Phone number update successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO<String>> deleteUser(Authentication authentication) {
        userService.deleteUser(authentication.getName());
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("User deleted successfully.");
        response.setData(String.format("User deleted successfully: %s", authentication.getName()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> updateUser(Authentication authentication, @RequestBody UserUpdateRequestDTO user) {
        User updatedUser = userService.updateUser(authentication.getName(), user);
        ResponseDTO<UserResponseDTO> response = new ResponseDTO<>();
        response.setData(UserResponseMapper.toDTO(updatedUser));
        response.setSuccess(true);
        response.setMessage("User updated successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
