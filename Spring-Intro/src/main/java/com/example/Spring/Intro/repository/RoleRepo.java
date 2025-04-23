package com.example.Spring.Intro.repository;

import com.example.Spring.Intro.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepo extends JpaRepository<UserRole, Long> {

    Set<UserRole> findByRoleName(String roleName);
}
