package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public MUser saveNewUser(MUser MUser){
        try {
            return userRepository.save(MUser);
        }catch (Exception e){
            return null;
        }
    }
    public MUser getUserById(Long id){
        return userRepository.findById(id).get();
    }
    public MUser getUserByPhone(String phone){
        return userRepository.findByPhonNumber(phone).orElse(null);
    }
}
