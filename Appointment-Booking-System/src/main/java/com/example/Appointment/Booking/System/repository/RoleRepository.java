package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRole(String name);
}
