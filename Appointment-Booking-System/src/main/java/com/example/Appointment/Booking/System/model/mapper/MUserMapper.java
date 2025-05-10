package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.MUserDto;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MUserMapper {

    public MUserDto mapToDto(MUser user) {
        MUserDto userDto = new MUserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPhonNumber(user.getPhonNumber());
        //userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setGender(user.getGender());
        userDto.setDateOfBirth(user.getDateOfBirth());

        Set<String> roles = new HashSet<>();
        for (UserRole userRole : user.getUserRoles()) {
            roles.add(userRole.getRole());
        }
        userDto.setRoles(roles);
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

        /*UserRole userRole = new UserRole();
        userRole.setRole("USER");
        userRole.setUsers(Set.of(user));

        user.setUserRoles(Set.of(userRole));*/

        return user;
    }
}
