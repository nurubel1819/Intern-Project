package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.*;


public interface UserService {
    MUser saveNewUser(MUser user);
    MUser getUserById(Long id);
    MUser getUserByPhone(String phone);
}
