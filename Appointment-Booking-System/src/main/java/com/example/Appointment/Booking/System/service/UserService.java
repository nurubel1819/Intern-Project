package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.*;

import java.util.List;


public interface UserService {
    MUser saveNewUser(MUser user);
    MUser getUserById(Long id);
    MUser getUserByPhone(String phone);
    MUser updateUser(MUser user);
    List<MUser> getAllUsers();
}
