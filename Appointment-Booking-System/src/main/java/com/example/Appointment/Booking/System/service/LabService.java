package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.Lab;
import java.util.List;


public interface LabService {
    Lab uploadLabDetails(Lab lab);
    Lab getLabDetails(String labName);
    List<Lab> getLabDetailsLike(String labName);
    Lab getLabDetailsById(Long id);
    List<Lab> getAllLabs();
    String deleteLab(Long id);
    Lab updateLabDetails(Lab lab);
}
