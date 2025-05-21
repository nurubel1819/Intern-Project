package com.example.Appointment.Booking.System.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LabTestAppointmentDto {
    @Schema(hidden = true)
    private Long id;
    private Long userId;
    private String labName;
    private List<String> labNames;
    private String testName;
    private String userPhone;
    private LocalDate bookingDate;
    private LocalDate deliveryDate;
    private String note;

}
