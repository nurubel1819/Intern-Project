package com.example.Appointment.Booking.System.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class LabTestAppointmentDto {
    @Schema(hidden = true)
    private Long id;
    private String labName;
    private String testName;
    private String userPhone;
    private OffsetDateTime bookingDate; //LocalDateTime
    private OffsetDateTime  appointmentDate;
    private String note;

}
