package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

@Data
public class DoctorAvailabilityDto {
    private Long doctorId;
    private int totalPossibilityPatient;
}
