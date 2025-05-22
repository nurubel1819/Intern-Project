package com.example.Appointment.Booking.System.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class DoctorAppointmentConfirmDto {
    private LocalDate date;
    private Long selectedTimeSlotId;
    private String note;
    private Long doctorId;
    private Long patientId;
    private String userPhone;
    private String loadForm;
}
