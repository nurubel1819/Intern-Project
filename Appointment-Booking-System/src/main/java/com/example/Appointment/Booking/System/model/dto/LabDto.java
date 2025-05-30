package com.example.Appointment.Booking.System.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabDto {
    @Schema(hidden = true)
    private Long id;
    private String labName;
    private String address;
    @Schema(hidden = true)
    private double rating;
    @Schema(hidden = true)
    private MultipartFile imageFile;
}
