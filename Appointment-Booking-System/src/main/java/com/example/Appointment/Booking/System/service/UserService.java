package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.*;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DoctorAppointmentService doctorAppointmentService;
    private final RoleService roleService;

    public MUser saveNewUser(MUser user){
        if(userRepository.findByPhonNumber(user.getPhonNumber()).isPresent()
        || userRepository.findByEmail(user.getEmail()).isPresent()) return null;
        try {
            MUser saveUser =  userRepository.save(user);
            UserRole role = roleService.findRoleByName("USER");
            if(role==null){
                roleService.addNewRole("USER");
                role = roleService.findRoleByName("USER");
            }
            roleService.setUserRole(saveUser.getId(),role.getId());
            return saveUser;
        }catch (Exception e){
            System.out.println("save user Error : "+e.getMessage());
            return null;
        }
    }
    public MUser getUserById(Long id){
        return userRepository.findById(id).get();
    }
    public MUser getUserByPhone(String phone){
        try {
            return userRepository.findByPhonNumber(phone).get();
        }catch (Exception e){
            return null;
        }
    }
}
