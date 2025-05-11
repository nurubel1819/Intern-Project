package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabTestRepository extends JpaRepository<LabTest,Long> {
    LabTest findByTestName(String name);
    List<LabTest> findByTestNameLike(String name);
}
