package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.DoctorAppointment;
import com.example.Appointment.Booking.System.model.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment,Long> {
    List<DoctorAppointment> findByUser(MUser user);
    List<DoctorAppointment> findByUserId(Long userId);
}
