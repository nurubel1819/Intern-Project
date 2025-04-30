package com.example.Spring.Intro.repository;

import com.example.Spring.Intro.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
