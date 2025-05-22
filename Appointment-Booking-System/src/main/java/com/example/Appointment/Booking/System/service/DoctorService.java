package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
import com.example.Appointment.Booking.System.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
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
            System.out.println("Exception = "+e.getMessage());
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
    public List<Doctor> getDoctorByNameLike(String name){
        try {
            return doctorRepository.findByNameContaining(name);
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
}
