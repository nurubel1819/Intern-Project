package com.example.Spring.Intro.model.mapper;

import com.example.Spring.Intro.model.dto.UserDto;
import com.example.Spring.Intro.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User mapToEntity(UserDto userDto)
    {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        return user;
    }
    public UserDto mapToDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }

}
