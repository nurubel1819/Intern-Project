package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoctorAppointmentDto {
    private LocalDateTime date;
    private String note;
    private Long doctorId;
    private Long patientId;
}
