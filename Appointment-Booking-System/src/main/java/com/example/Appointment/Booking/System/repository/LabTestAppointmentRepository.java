package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.LabTestAppointment;
import com.example.Appointment.Booking.System.model.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabTestAppointmentRepository extends JpaRepository<LabTestAppointment,Long> {
    List<LabTestAppointment> findByUserId(Long userId);
    List<LabTestAppointment> findByUser(MUser user);
}
