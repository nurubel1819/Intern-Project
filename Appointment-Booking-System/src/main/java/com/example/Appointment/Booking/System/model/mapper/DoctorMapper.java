package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.DoctorDto;
import com.example.Appointment.Booking.System.model.entity.Doctor;
import org.springframework.stereotype.Component;


@Component
public class DoctorMapper {

    public DoctorDto mapToDto(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setPhone(doctor.getPhone());
        doctorDto.setEmail(doctor.getEmail());
        doctorDto.setGender(doctor.getGender());
        doctorDto.setAddress(doctor.getAddress());
        doctorDto.setSpecialization(doctor.getSpecialization());
        doctorDto.setExperience(doctor.getExperience());
        doctorDto.setQualification(doctor.getQualification());
        doctorDto.setDateOfBirth(doctor.getDateOfBirth());
        doctorDto.setImageUrl(doctor.getImageUrl());
        return doctorDto;
    }

    public Doctor mapToEntity(DoctorDto doctorDto)
    {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDto.getName());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setGender(doctorDto.getGender());
        doctor.setAddress(doctorDto.getAddress());
        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setExperience(doctorDto.getExperience());
        doctor.setQualification(doctorDto.getQualification());
        doctor.setDateOfBirth(doctorDto.getDateOfBirth());
        doctor.setImageUrl(doctorDto.getImageUrl());
        return doctor;
    }
}
