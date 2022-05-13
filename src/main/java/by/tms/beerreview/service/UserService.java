package by.tms.beerreview.service;

import by.tms.beerreview.entity.User;
import by.tms.beerreview.mapper.UserConverter;
import by.tms.beerreview.dto.UserDTO;
import org.springframework.stereotype.Service;
import by.tms.beerreview.repository.UserRepository;

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