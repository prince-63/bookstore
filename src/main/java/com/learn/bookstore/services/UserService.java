package com.learn.bookstore.services;

import com.learn.bookstore.dto.RegisterUserRequestDTO;
import com.learn.bookstore.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(RegisterUserRequestDTO user);

    User getUserById(Long id);

    Optional<User> findByEmail(String email);

    List<User> getAllUsers();

    void deleteUser(Long id);

    User updateUser(Long id, User user);
}
