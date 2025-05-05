package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String gender;
    private String address;
    private String specialization;
    private String experience;
    private String qualification;
    private LocalDate dateOfBirth;
    private String image;
}
