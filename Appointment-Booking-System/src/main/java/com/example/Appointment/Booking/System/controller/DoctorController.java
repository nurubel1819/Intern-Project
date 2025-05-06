package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.DoctorDto;
import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.model.mapper.DoctorMapper;
import com.example.Appointment.Booking.System.service.DoctorService;
import com.example.Appointment.Booking.System.validation.ImportantValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    @PostMapping("/registration")
    private ResponseEntity<DoctorDto> registration(DoctorDto doctorDto){
        if(ImportantValidation.isValidBDPhone(doctorDto.getPhone()))
        {
            String phonNumber = doctorDto.getPhone();
            if(phonNumber.length() > 11) phonNumber = phonNumber.substring(3);
            doctorDto.setPhone(phonNumber);

            Doctor doctor = doctorMapper.mapToEntity(doctorDto);
            if(doctorDto.getEmail()!=null && ImportantValidation.isValidEmail(doctorDto.getEmail()))
            {
                return ResponseEntity.ok(doctorMapper.mapToDto(doctorService.uploadDoctor(doctor)));
            }
            else if(doctorDto.getEmail()==null)
                return ResponseEntity.ok(doctorMapper.mapToDto(doctorService.uploadDoctor(doctor)));
            else return ResponseEntity.badRequest().body(null);
        }
        else return ResponseEntity.badRequest().body(null);
    }
}
