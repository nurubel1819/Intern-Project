package com.example.Appointment.Booking.System.service.implementation;

import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
import com.example.Appointment.Booking.System.repository.UserRepository;
import com.example.Appointment.Booking.System.service.DoctorAppointmentService;
import com.example.Appointment.Booking.System.service.RoleService;
import com.example.Appointment.Booking.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final DoctorAppointmentService doctorAppointmentService;
    private final RoleService roleService;

    @Override
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
    @Override
    public MUser getUserById(Long id){

        return userRepository.findById(id).get();
    }
    @Override
    public MUser getUserByPhone(String phone){
        try {
            return userRepository.findByPhonNumber(phone).get();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public MUser updateUser(MUser user) {
        try {
            return userRepository.save(user);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<MUser> getAllUsers() {
        try {
            return userRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }

}
