package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.UserRole;

import java.util.List;

public interface RoleService {
    String addNewRole(String roleName);
    String setUserRole(Long userId,Long roleId);
    UserRole findRoleByName(String roleName);
    String deleteRole(String roleName);
    List<UserRole> getAllRoles();
}
