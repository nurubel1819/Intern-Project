package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.CountAppointment;
import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
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
    private final UserService userService;
    private final RoleService roleService;


    public Doctor uploadDoctor(Doctor doctor){

        try {
            Doctor saveDoctor = doctorRepository.save(doctor);
            MUser user = userService.getUserByPhone(doctor.getPhone());
            UserRole role = roleService.findRoleByName("DOCTOR");
            if(role==null){
                roleService.addNewRole("DOCTOR");
                role = roleService.findRoleByName("DOCTOR");
            }
            roleService.setUserRole(user.getId(),role.getId());
            return saveDoctor;
        }catch (Exception e){
            return null;
        }
    }
    public Doctor getByPhonNumber(String phonNumber){
        try {
            return doctorRepository.findByPhone(phonNumber);
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
