package com.example.beerreview.mapper;

import com.example.beerreview.dto.UserDTO;
import com.example.beerreview.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDto(User user);
    User userDtoToUser(UserDTO userDto);
}
