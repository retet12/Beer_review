package com.example.beerreview.mapper;

import com.example.beerreview.dto.UserDTO;
import com.example.beerreview.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDto(User user);
    User userDtoToUser(UserDTO userDto);
}
