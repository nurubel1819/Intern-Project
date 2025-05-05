package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String phonNumber;
    private String password;
    private String confirmPassword;
    private String email;
    private String gender;
    private LocalDate dateOfBirth;
}
