package by.tms.beerreview.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

//@SpringBootTest
//class UserServiceTest {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;

    //    @Test
//    void deleteUser() {
//        User user = new User();
//
//        List<Role> roles = new ArrayList<>();
//        Role role = new Role();
//        roles.add(role);
//
//        user.setFirstName("userFirstName");
//        user.setLastName("userLastName");
//        user.setUsername("userUsername");
//        user.setPassword("userPassword");
//        user.getRoleList();
//        role.setUser(user);
//        user.setPassword("userPassword");
//        user.setEmail("user@gmail.com");
//        user.setStatus(Status.DELETED);;
//
//        User saveActive = userRepository.save(user);
//        Role saveRole = roleRepository.save(role);
//        userService.deleteUser(saveActive);
//
//        assertEquals(Status.DELETED, saveActive.getStatus());
//    }
//    @Test
//    void registration() {
//        UserDTO userDto = new UserDTO();
//
//        userDto.setFirstName("userFirstName");
//        userDto.setLastName("userLastName");
//        userDto.setUsername("userUsername");
//        userDto.setPassword("userPassword");
//        userDto.setEmail("user@gmail.com");
//
//        userService.registration(userDto);
//
//        User user = userRepository.findByUsername(userDto.getUsername()).get();
//
//        assertEquals(userDto.getFirstName(), user.getFirstName());
//        assertEquals(userDto.getLastName(), user.getLastName());
//        assertEquals(userDto.getUsername(), user.getUsername());
//        assertFalse(user.getPassword().equals(userDto.getPassword()));
//        assertEquals(userDto.getEmail(), user.getEmail());
//        assertEquals(Status.ACTIVE, user.getStatus());
//        assertEquals("USER", user.getRoleList().get(0));
//    }
//}


