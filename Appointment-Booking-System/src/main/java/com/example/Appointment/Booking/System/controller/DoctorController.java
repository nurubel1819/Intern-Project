package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.DoctorAvailabilityDto;
import com.example.Appointment.Booking.System.model.dto.DoctorDto;
import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.mapper.DoctorMapper;
import com.example.Appointment.Booking.System.service.DoctorService;
import com.example.Appointment.Booking.System.service.UserService;
import com.example.Appointment.Booking.System.validation.ImportantValidation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/doctors")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final UserService userService;

    @PostMapping("/registration")
    private ResponseEntity<?> registration(DoctorDto doctorDto){

        if(!ImportantValidation.isValidBDPhone(doctorDto.getPhone()))
            return ResponseEntity.badRequest().body(Map.of("message","invalid phone number"));
        else {
            MUser user = userService.getUserByPhone(doctorDto.getPhone());
            if(user==null) return ResponseEntity.badRequest().body(Map.of("message","User Registration first then doctor registration"));
            else
            {
                if(doctorService.getByPhonNumber(doctorDto.getPhone())!=null)
                    return ResponseEntity.badRequest().body(Map.of("message","Phone or email already exists in database"));
                try {
                    doctorDto.setName(user.getName());
                    doctorDto.setEmail(user.getEmail());
                    doctorDto.setGender(user.getGender());
                    doctorDto.setDateOfBirth(user.getDateOfBirth());
                    Doctor doctor = doctorMapper.mapToEntity(doctorDto);
                    Doctor saveDoctor = doctorService.uploadDoctor(doctor);

                    return ResponseEntity.ok(doctorMapper.mapToDto(saveDoctor));
                }catch (Exception e){
                    System.out.println("Exception = "+e.getMessage());
                    return ResponseEntity.badRequest().body(Map.of("message","Save error from doctor controller"));
                }
            }
        }
    }
}
