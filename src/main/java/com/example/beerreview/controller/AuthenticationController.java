package com.example.beerreview.controller;

import com.example.beerreview.dto.UserDTO;
import com.example.beerreview.service.UserService;
import com.example.beerreview.configuration.security.jwt.JWTTokenProvider;
import com.example.beerreview.dto.AuthRequestDTO;
import com.example.beerreview.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;

    public AuthenticationController(UserService service, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> logIn(@RequestBody AuthRequestDTO requestDto){

        String username = requestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        User user = service.findByUsername(username);

        String token = jwtTokenProvider.generateToken(username, user.getRoleList());

        Map<Object, Object> resp = new HashMap<>();
        resp.put("username", username);
        resp.put("token", token);

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/reg")
    public ResponseEntity<UserDTO> registration(@RequestBody UserDTO userDTO){
        if (service.existByUsername(userDTO.getUsername()) || service.existByEmail(userDTO.getEmail())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.registration(userDTO);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping("/logout")
    public ResponseEntity<Map<Object, Object>> logOut(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<Object, Object> resp = new HashMap<>();

        if (auth != null) {
            resp.put("username", auth.getName());
            resp.put("session, lastAccessedTime", session.getLastAccessedTime());
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
