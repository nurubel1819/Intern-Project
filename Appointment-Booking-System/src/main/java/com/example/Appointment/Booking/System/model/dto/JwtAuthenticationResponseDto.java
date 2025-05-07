package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDto {
    private String token;
    private String refreshToken;
}
