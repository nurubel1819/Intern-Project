package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.RoleAddDto;
import com.example.Appointment.Booking.System.model.dto.RoleSetDto;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
import com.example.Appointment.Booking.System.service.RoleService;
import com.example.Appointment.Booking.System.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/roles")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;

    @PostMapping("/set_role")
    private ResponseEntity<?> setRole(@RequestBody RoleSetDto roleSetDto){
        roleSetDto.setRoleName(roleSetDto.getRoleName().toUpperCase());
        try {
            MUser user = userService.getUserByPhone(roleSetDto.getUserPhone());
            if(user == null) return ResponseEntity.badRequest().body(Map.of("Message = ","User(phone Number) not found in database create this user first"));
            UserRole role = roleService.findRoleByName(roleSetDto.getRoleName());
            if(role == null) return ResponseEntity.badRequest().body(Map.of("Message = ","Role not found in database create this role first"));
            return ResponseEntity.ok(roleService.setUserRole(user.getId(),role.getId())+"\nUser name = "+user.getName()+"\nNew role add = "+role.getRole());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("Message = ","User(phone Number) not found in database create this user first"));
        }
    }

    @PostMapping("/add_new_role")
    private ResponseEntity<?> addNewRole(@RequestBody RoleAddDto roleAddDto){
        roleAddDto.setRoleName(roleAddDto.getRoleName().toUpperCase());
        if(roleService.findRoleByName(roleAddDto.getRoleName()) == null){
            return ResponseEntity.ok(roleService.addNewRole(roleAddDto.getRoleName())+" Role name = "+roleAddDto.getRoleName());
        }
        else return ResponseEntity.badRequest().body(Map.of("Message = ","Role already exists"));
    }
    @DeleteMapping("/delete-role")
    private ResponseEntity<?> deleteRole(@RequestBody RoleAddDto deleteRoleDto){
        deleteRoleDto.setRoleName(deleteRoleDto.getRoleName().toUpperCase());
        return ResponseEntity.ok(roleService.deleteRole(deleteRoleDto.getRoleName()));
    }
}
