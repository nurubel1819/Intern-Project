package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.MUserDto;
import com.example.Appointment.Booking.System.model.entity.MUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MUserMapper {

    public MUserDto mapToDto(MUser MUser) {
        MUserDto MUserDto = new MUserDto();
        MUserDto.setId(MUser.getId());
        MUserDto.setName(MUser.getName());
        MUserDto.setPhonNumber(MUser.getPhonNumber());
        MUserDto.setPassword(MUser.getPassword());
        MUserDto.setEmail(MUser.getEmail());
        MUserDto.setGender(MUser.getGender());
        MUserDto.setDateOfBirth(MUser.getDateOfBirth());
        return MUserDto;
    }

    public MUser mapToEntity(MUserDto MUserDto){
        MUser MUser = new MUser();
        MUser.setName(MUserDto.getName());
        MUser.setPhonNumber(MUserDto.getPhonNumber());
        MUser.setPassword(MUserDto.getPassword());
        MUser.setEmail(MUserDto.getEmail());
        MUser.setGender(MUserDto.getGender());
        MUser.setDateOfBirth(MUserDto.getDateOfBirth());
        return MUser;
    }
}
