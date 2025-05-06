package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Patient, Long> {
    Patient findByPhonNumber(String phonNumber);
}
