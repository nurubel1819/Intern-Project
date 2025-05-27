package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class LabTestDto {

    private String testName;
    private String price;
    private String description;
    private String labName;
    private String testType;
    private Long durationInHours;
    private MultipartFile imageFile;
}
