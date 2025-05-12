package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.LabTestAppointment;
import com.example.Appointment.Booking.System.repository.LabTestAppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabTestAppointmentService {

    private final LabTestAppointmentRepository labTestAppointmentRepository;

    public LabTestAppointment bookNewAppointment(LabTestAppointment labTestAppointment){
        try {
            return labTestAppointmentRepository.save(labTestAppointment);
        }catch (Exception e){
            System.out.println("Exception lab test appointment book save = "+e.getMessage());
            return null;
        }
    }
}
