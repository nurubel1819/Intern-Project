package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.CountAppointment;
import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.repository.CountAppointmentRepository;
import com.example.Appointment.Booking.System.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final CountAppointmentRepository countAppointmentRepository;


    public Doctor uploadDoctor(Doctor doctor){

        try {
            return doctorRepository.save(doctor);
        }catch (Exception e){
            return null;
        }
    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor findDoctorById(Long id){
        try {
            return doctorRepository.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }

    public String setAppointment(Long doctorId, int totalAppointment){
        CountAppointment countAppointment = countAppointmentRepository.findByDoctorId(doctorId);
        if(countAppointment != null){
            return "You already set";
        }
        else {
            CountAppointment status = new CountAppointment();
            status.setDoctorId(doctorId);
            status.setTotalPatient(totalAppointment);
            try {
                countAppointmentRepository.save(status);
                return "Successfully set appointment to doctor";
            }catch (Exception e){
                return "Not set error = "+e.getMessage();
            }
        }
    }


}
