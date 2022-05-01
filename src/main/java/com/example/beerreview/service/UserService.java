package com.example.beerreview.service;

import com.example.beerreview.dto.UserDTO;
import com.example.beerreview.entity.Role;
import com.example.beerreview.entity.Status;
import com.example.beerreview.entity.User;
import com.example.beerreview.mapper.UserConverter;
import org.springframework.stereotype.Service;
import com.example.beerreview.repository.RoleRepository;
import com.example.beerreview.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void registration(UserDTO userDTO) {
        User user = UserConverter.convertToUserFromUserSignupDTO(userDTO);
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setTypeOfRole("USER");
        roles.add(role);
        user.setRoleList(roles);
        user.setStatus(Status.ACTIVE);
        role.setUser(user);
        userRepository.save(user);
        roleRepository.save(role);
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