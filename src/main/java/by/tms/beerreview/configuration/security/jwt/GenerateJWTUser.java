package by.tms.beerreview.configuration.security.jwt;

import by.tms.beerreview.entity.Role;
import by.tms.beerreview.entity.Status;
import by.tms.beerreview.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateJWTUser {
    public static JWTUser create(User user){
        return new JWTUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoleList())),
                user.getStatus().equals(Status.ACTIVE)
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getTypeOfRole()))
                .collect(Collectors.toList());
    }

}
