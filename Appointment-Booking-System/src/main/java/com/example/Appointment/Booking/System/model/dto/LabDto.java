package com.example.Appointment.Booking.System.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabDto {
    private Long id;
    private String labName;
    private String address;
    private double rating;
}
