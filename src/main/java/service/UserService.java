package service;

import dto.UserDTO;
import entity.Role;
import entity.Status;
import entity.User;
import mapper.UserConverter;
import org.springframework.stereotype.Service;
import repository.RoleRepository;
import repository.UserRepository;

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