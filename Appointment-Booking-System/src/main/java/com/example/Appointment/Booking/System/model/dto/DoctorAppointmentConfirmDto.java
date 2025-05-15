package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DoctorAppointmentConfirmDto {
    private LocalDateTime date;
    private String note;
    private Long doctorId;
    private String userPhone;
}
