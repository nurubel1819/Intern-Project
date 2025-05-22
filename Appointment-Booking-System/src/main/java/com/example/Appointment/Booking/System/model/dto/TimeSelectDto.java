package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeSelectDto {
    private Long slotId;
    private LocalTime time;
    private Long doctorId;
    private Long patientId;
}
