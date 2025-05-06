package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.PatientDto;
import com.example.Appointment.Booking.System.model.entity.Patient;
import com.example.Appointment.Booking.System.model.mapper.PatientMapper;
import com.example.Appointment.Booking.System.service.UserService;
import com.example.Appointment.Booking.System.validation.ImportantValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class PatientController {
    private final UserService userService;
    private final PatientMapper patientMapper;

    @PostMapping("/registration")
    private ResponseEntity<PatientDto> registration(PatientDto patientDto){
        if(ImportantValidation.isValidBDPhone(patientDto.getPhonNumber()))
        {
            // remove +88 from BD phone number
            String phonNumber = patientDto.getPhonNumber();
            if(phonNumber.length() > 11) phonNumber = phonNumber.substring(3);
            patientDto.setPhonNumber(phonNumber);

            Patient patient = patientMapper.mapToEntity(patientDto);
            if(patientDto.getEmail() != null && ImportantValidation.isValidEmail(patientDto.getEmail()))
            {
                return ResponseEntity.ok(patientMapper.mapToDto(userService.saveNewUser(patient)));
            }
            else if(patientDto.getEmail() == null) return ResponseEntity.ok(patientMapper.mapToDto(userService.saveNewUser(patient)));
            else return ResponseEntity.badRequest().body(null);
        }
        else return ResponseEntity.badRequest().body(null);
    }
}
