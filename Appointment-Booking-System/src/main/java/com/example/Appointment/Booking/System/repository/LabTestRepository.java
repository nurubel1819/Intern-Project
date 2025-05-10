package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabTestRepository extends JpaRepository<LabTest,Long> {
    LabTest findByTestName(String name);
    LabTest findByTestNameLike(String name);
}
