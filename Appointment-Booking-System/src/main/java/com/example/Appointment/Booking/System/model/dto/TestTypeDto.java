package com.example.Appointment.Booking.System.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestTypeDto {
    @Schema(hidden = true)
    private Long id;
    private String name;
    private String description;
}
