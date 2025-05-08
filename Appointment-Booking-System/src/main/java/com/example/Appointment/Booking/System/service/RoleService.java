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
    public String SetRole(/*RoleDto roleDto*/ Long userId,Long roleId)
    {
        try {
            UserRole role = roleRepository.findById(roleId).get();
            if (role == null) {
                return "Role not found!";
            }

            MUser roleUser = userRepository.findById(userId).get();

            Set<MUser> updatedUserSet = role.getUsers();
            if (updatedUserSet == null) {
                updatedUserSet = new HashSet<>();
            }
            updatedUserSet.add(roleUser);
            role.setUsers(updatedUserSet);

            UserRole save_role = roleRepository.save(role);

            return "Role set successfully"+" Role Id : "+save_role.getId()+" User Id : "+save_role.getUsers().stream().map(MUser::getId).toList();
        }
        catch (Exception e)
        {
            return "Role not added"+" Exception : "+e.getMessage();
        }
    }
    public String setRoleInUser(Long userId,Long roleId)
    {
        MUser user = userRepository.findById(userId).get();
        if(user==null)
        {
            return "User not found";
        }
        else
        {
            UserRole userRole = roleRepository.findById(roleId).get();
            if(userRole==null)
            {
                return "Role not found";
            }
            else
            {
                try {
                    Set<UserRole> allRole = user.getUserRoles();
                    allRole.add(userRole);
                    user.setUserRoles(allRole);
                    MUser role_user = userRepository.save(user);
                    return "Role set successfully"+role_user;
                }catch (Exception e){
                    return "Role not set. exception : "+e.getMessage();
                }
            }
        }

    }
    public UserRole findRoleByName(String roleName)
    {
        return roleRepository.findByRole(roleName);
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
