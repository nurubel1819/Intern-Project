package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

@Data
public class DoctorAvailableStatusDto {
    private Long id;
    private String name;
    private String qualification;
    private String specialization;
    private String status;
    private int totalPossibilityPatient;
    private String imageUrl;
}
