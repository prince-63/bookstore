package com.learn.bookstore.services;

import com.learn.bookstore.dto.RegisterUserRequestDTO;
import com.learn.bookstore.dto.UserUpdateRequestDTO;
import com.learn.bookstore.models.User;

import java.util.List;

public interface UserService {

    User register(RegisterUserRequestDTO user);

    User getUserById(Long id);

    User findByEmail(String email);

    List<User> getAllUsers();

    User addPhoneNumber(String email, String phone);

    void deleteUser(String email);

    User updateUser(String email, UserUpdateRequestDTO user);
}
