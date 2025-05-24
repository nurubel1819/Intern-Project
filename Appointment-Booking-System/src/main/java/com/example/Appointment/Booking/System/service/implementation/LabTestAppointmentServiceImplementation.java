package com.example.Appointment.Booking.System.service.implementation;

import com.example.Appointment.Booking.System.model.entity.LabTestAppointment;
import com.example.Appointment.Booking.System.repository.LabTestAppointmentRepository;
import com.example.Appointment.Booking.System.service.LabTestAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabTestAppointmentServiceImplementation implements LabTestAppointmentService {
    private final LabTestAppointmentRepository labTestAppointmentRepository;

    @Override
    public LabTestAppointment bookNewAppointment(LabTestAppointment labTestAppointment){
        try {
            return labTestAppointmentRepository.save(labTestAppointment);
        }catch (Exception e){
            System.out.println("Exception lab test appointment book save = "+e.getMessage());
            return null;
        }
    }
    @Override
    public List<LabTestAppointment> getOneUserHistory(Long userId){
        return labTestAppointmentRepository.findByUserId(userId);
    }
}
