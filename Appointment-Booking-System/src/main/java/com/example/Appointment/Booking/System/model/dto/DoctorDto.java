package com.example.Appointment.Booking.System.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorDto {
    @Schema(hidden = true)
    private Long id;
    @Schema(hidden = true)
    private String name;
    private String phone;
    @Schema(hidden = true)
    private String email;
    @Schema(hidden = true)
    private String gender;
    private String address;
    private String specialization;
    private String experience;
    private String qualification;
    @Schema(hidden = true)
    private LocalDate dateOfBirth;
    @Schema(hidden = true)
    private String image;
}
