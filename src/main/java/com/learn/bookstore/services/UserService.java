package com.learn.bookstore.services;

import com.learn.bookstore.dto.user.RegisterUserRequestDTO;
import com.learn.bookstore.dto.user.UserUpdateRequestDTO;
import com.learn.bookstore.models.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    User register(RegisterUserRequestDTO user);

    User getUserById(Long id);

    User findByEmail(Authentication authorization);

    List<User> getAllUsers();

    User addPhoneNumber(Authentication authentication, String phone);

    void deleteUser(Authentication authentication);

    User updateUser(Authentication authentication, UserUpdateRequestDTO user);
}
