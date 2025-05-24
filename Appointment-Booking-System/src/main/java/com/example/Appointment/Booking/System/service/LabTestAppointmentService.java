package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.LabTestAppointment;
import java.util.List;

public interface LabTestAppointmentService {
    LabTestAppointment bookNewAppointment(LabTestAppointment labTestAppointment);
    List<LabTestAppointment> getOneUserHistory(Long userId);
}
