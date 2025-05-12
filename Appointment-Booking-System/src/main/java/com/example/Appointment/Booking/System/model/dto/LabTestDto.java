package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;


@Data
public class LabTestDto {

    private String testName;
    private String price;
    private String description;
    private String labName;
    private String testType;
    private Long durationInHours;
}
