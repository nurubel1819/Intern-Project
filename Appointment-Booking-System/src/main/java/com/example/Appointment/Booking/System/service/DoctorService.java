package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.dto.DoctorAvailabilityDto;
import com.example.Appointment.Booking.System.model.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor uploadDoctor(Doctor doctor);
    Doctor getByPhonNumber(String phonNumber);
    List<Doctor> getDoctorByNameLike(String name);
    List<Doctor> getAllDoctors();
    Doctor findDoctorById(Long id);
    Doctor updateDoctorDetails(Doctor doctor);
    boolean setDoctorAvailability(DoctorAvailabilityDto dto);
}
