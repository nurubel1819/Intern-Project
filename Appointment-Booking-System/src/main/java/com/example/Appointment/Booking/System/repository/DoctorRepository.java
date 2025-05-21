package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByPhone(String phone);
    List<Doctor> findByNameContaining(String name);
}
