package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.CountAppointment;
import com.example.Appointment.Booking.System.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountAppointmentRepository extends JpaRepository<CountAppointment,Long> {
    CountAppointment findById(long id);

    CountAppointment findByDoctorId(Long doctorId);
}
