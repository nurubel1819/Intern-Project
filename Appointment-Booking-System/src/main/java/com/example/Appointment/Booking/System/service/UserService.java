package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.User;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveNewUser(User user){
        try {
            return userRepository.save(user);
        }catch (Exception e){
            return null;
        }
    }
}
