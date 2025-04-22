package com.example.Spring.Intro.model.mapper;

import com.example.Spring.Intro.model.dto.UserDto;
import com.example.Spring.Intro.model.entity.UserEntity;

public class UserMapper {
    public static UserEntity mapEntity(UserDto userDto)
    {
        return new UserEntity(userDto.getId(),userDto.getName());
    }
    public static UserDto mapToDto(UserEntity userEntity)
    {
        return new UserDto(userEntity.getId(),userEntity.getName());
    }
}
