package com.learn.bookstore.services.impl;

import com.learn.bookstore.dto.RegisterUserRequestDTO;
import com.learn.bookstore.dto.UserUpdateRequestDTO;
import com.learn.bookstore.exceptions.ResourceNotFoundException;
import com.learn.bookstore.models.Role;
import com.learn.bookstore.models.User;
import com.learn.bookstore.repositories.UserRepository;
import com.learn.bookstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public User getUserById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User","userId", id.toString())
        );
    }

    @Override
    public User findByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User","email", email)
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addPhoneNumber(String email, String phone) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User","email", email)
        );
        user.setPhone(phone);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User","email", email)
        );
        userRepository.delete(user);
    }

    @Override
    public User updateUser(String email, UserUpdateRequestDTO user) throws ResourceNotFoundException {
        User dbUser = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User","email", email)
        );
        dbUser.setEmail(user.email() != null ? user.email() : dbUser.getEmail());
        dbUser.setName(user.name() != null ? user.name() : dbUser.getName());
        dbUser.setPhone(user.phone() != null ? user.phone() : dbUser.getPhone());
        return userRepository.save(dbUser);
    }

}
