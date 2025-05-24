package com.example.Appointment.Booking.System.service.implementation;

import com.example.Appointment.Booking.System.model.entity.DoctorAppointmentHistory;
import com.example.Appointment.Booking.System.repository.DoctorAppointmentHistoryRepository;
import com.example.Appointment.Booking.System.repository.DoctorRepository;
import com.example.Appointment.Booking.System.repository.UserRepository;
import com.example.Appointment.Booking.System.service.DoctorAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorAppointmentServiceImplementation implements DoctorAppointmentService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorAppointmentHistoryRepository historyRepository;

    @Override
    public String saveAppointmentHistory(DoctorAppointmentHistory history)
    {
        try {
            historyRepository.save(history);
            return "save appointment history";
        }catch (Exception e){
            return "Error";
        }
    }
    @Override
    public List<DoctorAppointmentHistory> getHistoryByPatientId(Long id)
    {
        try {
            return historyRepository.findByPatientId(id);
        }catch (Exception e){
            return null;
        }
    }
}
