package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.DoctorAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment,Long> {
}
