package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.RoleSetDto;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
import com.example.Appointment.Booking.System.service.RoleService;
import com.example.Appointment.Booking.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;

    private static final String[] ROLES = {"ROLE_USER", "ROLE_ADMIN","ROLE_DOCTOR","ROLE_LAB","ROLE_TESTER"};


    @PostMapping("/set_role")
    private String setRole(@RequestBody RoleSetDto roleSetDto){
        MUser user = userService.getUserByPhone(roleSetDto.getUserPhone());
        UserRole role = roleService.findRoleByName(roleSetDto.getRoleName());
        System.out.println("user = "+user+" role = "+role);
        if(user != null && role != null){
            return roleService.SetRole(user.getId(),role.getId());
        }
        else return "role can't be set ";
    }

    @PostMapping("/add_new_role")
    private String addNewRole(@RequestBody RoleSetDto roleSetDto){
        if(roleService.findRoleByName(roleSetDto.getRoleName()) == null){
            return roleService.addNewRole(roleSetDto.getRoleName());
        }
        else return "role already exists";
    }
}
