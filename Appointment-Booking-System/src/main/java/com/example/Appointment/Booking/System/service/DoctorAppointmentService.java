package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.DoctorAppointmentHistory;
import java.util.List;


public interface DoctorAppointmentService {
    String saveAppointmentHistory(DoctorAppointmentHistory history);
    List<DoctorAppointmentHistory> getHistoryByPatientId(Long id);
}
