package com.learn.bookstore.services.impl;

import com.learn.bookstore.dto.RegisterUserRequestDTO;
import com.learn.bookstore.models.Role;
import com.learn.bookstore.models.User;
import com.learn.bookstore.repositories.UserRepository;
import com.learn.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterUserRequestDTO registerUserRequestDTO) {
        String hashPwd = passwordEncoder.encode(registerUserRequestDTO.password());
        User user = User.builder()
                .name(registerUserRequestDTO.name())
                .email(registerUserRequestDTO.email())
                .password(hashPwd)
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

}
