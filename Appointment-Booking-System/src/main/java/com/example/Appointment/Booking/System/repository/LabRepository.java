package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab, Long> {
    Lab findByLabName(String labName);
    List<Lab> findByLabNameLike(String labName);
}
