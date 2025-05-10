package com.example.Appointment.Booking.System.model.dto;

import com.example.Appointment.Booking.System.model.entity.Lab;
import com.example.Appointment.Booking.System.model.entity.TestType;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class LabTestDto {

    private String testName;
    private String price;
    private String description;
    private String duration;
    private String labName;
    private TestType testType;

}
