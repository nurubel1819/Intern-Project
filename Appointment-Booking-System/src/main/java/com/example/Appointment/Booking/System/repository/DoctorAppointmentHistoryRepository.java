package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.DoctorAppointmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorAppointmentHistoryRepository extends JpaRepository<DoctorAppointmentHistory, Long> {
    List<DoctorAppointmentHistory> findByPatientId(Long patientId);
}
