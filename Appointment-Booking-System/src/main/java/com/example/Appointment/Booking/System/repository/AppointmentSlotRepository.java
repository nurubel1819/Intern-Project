package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {
    List<AppointmentSlot> findByDoctorIdAndDateAndBookedFalse(Long doctorId, LocalDate date);
}
