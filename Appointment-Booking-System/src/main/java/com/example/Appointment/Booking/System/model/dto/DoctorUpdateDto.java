package com.example.Appointment.Booking.System.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DoctorUpdateDto {
    private String phone;
    private String name;
    private String qualification;
    private String specialization;
    private String address;
    private String experience;
    @Schema(hidden = true)
    private String image;
}
