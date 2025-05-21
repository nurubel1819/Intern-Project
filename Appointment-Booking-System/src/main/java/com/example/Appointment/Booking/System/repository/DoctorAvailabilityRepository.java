package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {
    Optional<DoctorAvailability> findByDoctorIdAndDate(Long doctorId, LocalDate date);
    boolean existsByDoctorIdAndDate(Long doctorId, LocalDate date);
}
