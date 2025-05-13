package com.example.Appointment.Booking.System.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;


@Data
public class MUserDto {

    @JsonIgnore
    private Long id;
    private String name;
    private String phonNumber;
    private String password;
    private String confirmPassword;
    private String email;
    private String gender;
    private LocalDate dateOfBirth;
    private Set<String> roles;
}
