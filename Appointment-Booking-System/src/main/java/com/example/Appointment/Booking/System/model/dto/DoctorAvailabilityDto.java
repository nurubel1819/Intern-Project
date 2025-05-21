package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorAvailabilityDto {
    private Long doctorId;
    private LocalDate date;
    private boolean available;
    private int totalPossibilityPatient;
}
