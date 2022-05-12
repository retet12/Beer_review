package com.example.beerreview.service;

import com.example.beerreview.dto.UserDTO;
import com.example.beerreview.entity.User;
import com.example.beerreview.mapper.UserConverter;
import org.springframework.stereotype.Service;
import com.example.beerreview.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registration(UserDTO userDTO) {
        User user = UserConverter.convertToUserFromUserSignupDTO(userDTO);
        userRepository.save(user);
    }
    public void deleteUser(User user) {
        User deleted = userRepository.save(user);

    }
    public User findByUsername(String username) {
        User byUsername = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username: " + username + " not found"));
        return byUsername;
    }

    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}