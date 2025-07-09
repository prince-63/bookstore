package com.learn.bookstore.mappers;


import com.learn.bookstore.dto.user.UserResponseDTO;
import com.learn.bookstore.models.User;

public class UserResponseMapper {

    public static UserResponseDTO UserToUserResponseMapper(User user) {
        return UserResponseDTO.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).phone(user.getPhone()).role(user.getRole().name()).build();
    }

}
