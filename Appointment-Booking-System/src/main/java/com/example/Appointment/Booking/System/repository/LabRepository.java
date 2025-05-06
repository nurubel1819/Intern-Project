package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRepository extends JpaRepository<Lab, Long> {
    Lab findByLabName(String labName);
}
