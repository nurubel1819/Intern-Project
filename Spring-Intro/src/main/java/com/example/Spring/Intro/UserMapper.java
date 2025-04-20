package com.example.Spring.Intro;

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
