package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
import com.example.Appointment.Booking.System.repository.RoleRepository;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public String addNewRole(String roleName)
    {
        UserRole saveRole = roleRepository.findByRole(roleName);
        if(saveRole == null)
        {
            UserRole userRole = new UserRole();
            userRole.setRole(roleName);
            roleRepository.save(userRole);
            return "Role Added Successfully";
        }
        else return "Role Already Exists";
    }

    public String setUserRole(Long userId,Long roleId)
    {
        try {
            UserRole userRole = roleRepository.findById(roleId).get();
            MUser user = userRepository.findById(userId).get();
            Set<MUser> users = userRole.getUsers();
            users.add(user);
            userRole.setUsers(users);
            roleRepository.save(userRole);
            return "User Role Set Successfully";
        }catch (Exception e){
            return "User Role Not set. Exception = "+e.getMessage();
        }

    }

    public UserRole findRoleByName(String roleName)
    {
        try {
            return roleRepository.findByRole(roleName);
        }catch (Exception e){
            return null;
        }
    }
    public String deleteRole(String roleName)
    {
        UserRole userRole = roleRepository.findByRole(roleName);
        if(userRole != null)
        {
            roleRepository.delete(userRole);
            return "Role Deleted Successfully";
        }
        else return "Role Not Found";
    }
    public String updateRole(String oldRoleName, String newRoleName)
    {
        UserRole userRole = roleRepository.findByRole(oldRoleName);
        if(userRole != null)
        {
            userRole.setRole(newRoleName);
            roleRepository.save(userRole);
            return "Role Updated Successfully";
        }
        else return "Role Not Found";
    }
}
