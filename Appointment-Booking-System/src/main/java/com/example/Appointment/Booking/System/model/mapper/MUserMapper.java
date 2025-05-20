package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.MUserDto;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
import com.example.Appointment.Booking.System.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MUserMapper {

    private final RoleService roleService;

    public MUserDto mapToDto(MUser user) {
        MUserDto userDto = new MUserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPhonNumber(user.getPhonNumber());
        userDto.setEmail(user.getEmail());
        userDto.setGender(user.getGender());
        userDto.setDateOfBirth(user.getDateOfBirth());
        return userDto;
    }

    public MUser mapToEntity(MUserDto MUserDto){
        MUser user = new MUser();
        user.setName(MUserDto.getName());
        user.setPhonNumber(MUserDto.getPhonNumber());
        user.setPassword(MUserDto.getPassword());
        user.setEmail(MUserDto.getEmail());
        user.setGender(MUserDto.getGender());
        user.setDateOfBirth(MUserDto.getDateOfBirth());
        return user;
    }
}
