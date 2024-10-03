package com.arsenyzh.authservice.mapper;

import com.arsenyzh.authservice.dto.UserDto;
import com.arsenyzh.authservice.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    public User convertUserDtoToUser (UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        return user;
    }

    public UserDto convertUserToUserDto (User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;
    }
}
