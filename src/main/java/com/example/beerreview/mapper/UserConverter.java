package com.example.beerreview.mapper;

import com.example.beerreview.dto.UserDTO;
import com.example.beerreview.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private static BCryptPasswordEncoder passwordEncoder;

    public UserConverter(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static User convertToUserFromUserSignupDTO(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhone())
                .build();
    }
}