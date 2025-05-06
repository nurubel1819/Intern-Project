package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.Patient;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Patient saveNewUser(Patient patient){
        try {
            return userRepository.save(patient);
        }catch (Exception e){
            return null;
        }
    }
}
