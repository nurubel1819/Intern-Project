package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.UserDto;
import com.example.Appointment.Booking.System.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPhonNumber(user.getPhonNumber());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setGender(user.getGender());
        userDto.setDateOfBirth(user.getDateOfBirth());
        return userDto;
    }

    public User mapToEntity(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setPhonNumber(userDto.getPhonNumber());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setGender(userDto.getGender());
        user.setDateOfBirth(userDto.getDateOfBirth());
        return user;
    }
}
