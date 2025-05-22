package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.model.entity.DoctorAppointmentHistory;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.repository.DoctorAppointmentHistoryRepository;
import com.example.Appointment.Booking.System.repository.DoctorRepository;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorAppointmentService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorAppointmentHistoryRepository historyRepository;

    public String saveAppointmentHistory(DoctorAppointmentHistory history)
    {
        try {
            historyRepository.save(history);
            return "save appointment history";
        }catch (Exception e){
            return "Error";
        }
    }
    public List<DoctorAppointmentHistory> getHistoryByPatientId(Long id)
    {
        try {
            return historyRepository.findByPatientId(id);
        }catch (Exception e){
            return null;
        }
    }
}
