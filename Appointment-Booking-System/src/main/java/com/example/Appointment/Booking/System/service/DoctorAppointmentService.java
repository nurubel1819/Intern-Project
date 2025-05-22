package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.model.entity.DoctorAppointment;
import com.example.Appointment.Booking.System.model.entity.DoctorAppointmentHistory;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.repository.DoctorAppointmentHistoryRepository;
import com.example.Appointment.Booking.System.repository.DoctorAppointmentRepository;
import com.example.Appointment.Booking.System.repository.DoctorRepository;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorAppointmentService {
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorAppointmentHistoryRepository historyRepository;

    public DoctorAppointment addDoctorAppointment(Long doctorId, Long patientId,int appointmentTime) {
        DoctorAppointment doctorAppointment = new DoctorAppointment();
        doctorAppointment.setBookingDate(LocalDateTime.now());
        doctorAppointment.setAppointmentDate(LocalDateTime.now());//ann time
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        MUser user = userRepository.findById(patientId).orElse(null);
        doctorAppointment.setDoctor(doctor);
        doctorAppointment.setUser(user);
        return doctorAppointmentRepository.save(doctorAppointment);
    }
    public List<DoctorAppointment> getHistory(Long userId)
    {
        try {
            System.out.println("before user");
            MUser user = userRepository.findById(userId).get();
            System.out.println("user = "+user);
            return doctorAppointmentRepository.findByUser(user);
        }catch (Exception e){
            System.out.println("Exception get history = "+e.getMessage());
            return null;
        }
    }
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
