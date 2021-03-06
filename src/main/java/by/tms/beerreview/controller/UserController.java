package by.tms.beerreview.controller;

import by.tms.beerreview.entity.User;
import by.tms.beerreview.exception.ExistsException;
import by.tms.beerreview.exception.InvalidException;
import by.tms.beerreview.exception.NotFoundException;
import by.tms.beerreview.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "User", description = "Operation about user")
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @ApiOperation(value = "Get user by user name", authorizations = {@Authorization(value = "apiKey")})
    @GetMapping(value = "/{username}", produces = "application/json")
    public ResponseEntity<User> get(@ApiParam(value = "The name that needs to be fetched. Use user1 for testing", example = "username")
                                    @PathVariable("username") String username) {

        if (username == null | userRepository.findByUsername(username).isEmpty()) {
            throw new NotFoundException();
        }
        User getUser = userRepository.findByUsername(username).get();

        return ResponseEntity.ok(getUser);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "405", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @ApiOperation(value = "Create user", notes = "This can only be done by the logged in user")
    @PostMapping(produces = "application/json")
    public ResponseEntity<User> save(@ApiParam(value = "Created user object", name = "body")
                                     @Valid @RequestBody User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new InvalidException();
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ExistsException();
        }

        User save = userRepository.save(user);

        return ResponseEntity.ok(save);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @ApiOperation(value = "Updated user", notes = "This can only be done by the logged in user", authorizations = {@Authorization(value = "apiKey")})
    @PutMapping(value = "/{username}", produces = "application/json")
    public ResponseEntity<User> update(@ApiParam(value = "username that need to be updated", example = "username")
                                       @PathVariable("username") String username,
                                       @ApiParam(value = "Updated user object", example = "user") @Valid @RequestBody User user,
                                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new InvalidException();
        }

        if (username == null | userRepository.findByUsername(username).isEmpty()) {
            throw new NotFoundException();
        }
        User update = userRepository.findByUsername(username).get();
        user.setId(update.getId());
        userRepository.save(user);

        return ResponseEntity.ok(update);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @ApiOperation(value = "Delete user", notes = "This can only be done by the logged in user", authorizations = {@Authorization(value = "apiKey")})
    @DeleteMapping(value = "/{username}", produces = "application/json")
    public void deleteUser(@ApiParam(value = "username that need to be deleted", example = "username")
                           @PathVariable("username") String username) {

        if (username == null | userRepository.findByUsername(username).isEmpty()) {
            throw new NotFoundException();
        }
        User user = userRepository.findByUsername(username).get();
        userRepository.delete(user);
    }
}