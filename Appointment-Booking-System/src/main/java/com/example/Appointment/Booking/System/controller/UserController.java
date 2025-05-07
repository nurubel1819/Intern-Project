package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.JwtAuthenticationResponseDto;
import com.example.Appointment.Booking.System.model.dto.MUserDto;
import com.example.Appointment.Booking.System.model.dto.SignInRequestDto;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.mapper.MUserMapper;
import com.example.Appointment.Booking.System.service.AuthenticationService;
import com.example.Appointment.Booking.System.service.UserService;
import com.example.Appointment.Booking.System.validation.ImportantValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final MUserMapper MUserMapper;

    @PostMapping("/registration")
    private ResponseEntity<MUserDto> registration(MUserDto userDto){
        if(ImportantValidation.isValidBDPhone(userDto.getPhonNumber()))
        {
            // remove +88 from BD phone number
            String phonNumber = userDto.getPhonNumber();
            if(phonNumber.length() > 11) phonNumber = phonNumber.substring(3);
            userDto.setPhonNumber(phonNumber);

            MUser user = MUserMapper.mapToEntity(userDto);
            if(userDto.getEmail() != null && ImportantValidation.isValidEmail(userDto.getEmail()))
            {
                return ResponseEntity.ok(MUserMapper.mapToDto(authenticationService.sinUp(user)));
            }
            else if(userDto.getEmail() == null) return ResponseEntity.ok(MUserMapper.mapToDto(authenticationService.sinUp(user)));
            else return ResponseEntity.badRequest().body(null);
        }
        else return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/login")
    private ResponseEntity<JwtAuthenticationResponseDto> login(@RequestBody SignInRequestDto signInRequestDto){
        if(ImportantValidation.isValidBDPhone(signInRequestDto.getPhone()))
        {
            String phonNumber = signInRequestDto.getPhone();
            if(phonNumber.length() > 11) phonNumber = phonNumber.substring(3);
            signInRequestDto.setPhone(phonNumber);
            return ResponseEntity.ok(authenticationService.signIn(signInRequestDto));
        }
        else return ResponseEntity.badRequest().body(null);
    }
}
