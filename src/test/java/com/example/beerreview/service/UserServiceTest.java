package com.example.beerreview.service;

import com.example.beerreview.entity.Role;
import com.example.beerreview.entity.Status;
import com.example.beerreview.entity.User;
import com.example.beerreview.repository.RoleRepository;
import com.example.beerreview.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Test
    void deleteUser() {
        User user = new User();

        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        roles.add(role);

        user.setFirstName("userFirstName");
        user.setLastName("userLastName");
        user.setUsername("userUsername");
        user.setPassword("userPassword");
        user.getRoleList();
        role.setUser(user);
        user.setPassword("userPassword");
        user.setEmail("user@gmail.com");
        user.setStatus(Status.DELETED);;

        User saveActive = userRepository.save(user);
        Role saveRole = roleRepository.save(role);
        userService.deleteUser(saveActive);

        assertEquals(Status.DELETED, saveActive.getStatus());
    }
}


