package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.UserDto;
import com.example.Appointment.Booking.System.model.entity.User;
import com.example.Appointment.Booking.System.model.mapper.UserMapper;
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
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/registration")
    private ResponseEntity<UserDto> registration(UserDto userDto){
        if(ImportantValidation.isValidBDPhone(userDto.getPhonNumber()))
        {
            // remove +88 from BD phone number
            String phonNumber = userDto.getPhonNumber();
            if(phonNumber.length() > 11) phonNumber = phonNumber.substring(3);
            userDto.setPhonNumber(phonNumber);

            User user = userMapper.mapToEntity(userDto);
            if(userDto.getEmail() != null && ImportantValidation.isValidEmail(userDto.getEmail()))
            {
                return ResponseEntity.ok(userMapper.mapToDto(userService.saveNewUser(user)));
            }
            else if(userDto.getEmail() == null) return ResponseEntity.ok(userMapper.mapToDto(userService.saveNewUser(user)));
            else return ResponseEntity.badRequest().body(null);
        }
        else return ResponseEntity.badRequest().body(null);
    }
}
