package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class LabTestAppointmentDto {
    private Long id;
    private String labName;
    private String testName;
    private String userPhone;
    private OffsetDateTime bookingDate; //LocalDateTime
    private OffsetDateTime  appointmentDate;
    private String note;

}
