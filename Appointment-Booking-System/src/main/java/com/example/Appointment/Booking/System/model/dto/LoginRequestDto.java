package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String phone;
    private String password;
    private String token;
}
