package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoadFormDto {
    private Long doctorId;
    private String loadStatus;
    private LocalDate date;
}
