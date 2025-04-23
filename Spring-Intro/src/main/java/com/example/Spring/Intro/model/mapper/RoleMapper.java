package com.example.Spring.Intro.model.mapper;

import com.example.Spring.Intro.model.dto.RoleDto;
import com.example.Spring.Intro.model.entity.UserRole;
import com.example.Spring.Intro.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final RoleRepo roleRepo;

    public UserRole mapToUserRole(RoleDto roleDto)
    {
        UserRole userRole = new UserRole();
        userRole.setId(roleDto.getId());
        userRole.setRoleName(roleDto.getRoleName());
        userRole.setDescription(roleDto.getDescription());
        /*
        Set<UserRole> userRoles = roleRepo.findByRoleName(roleDto.getRoleName());
        userRole.setUser(userRoles.addAll());
        userRole.setUser(roleRepo.findByRoleName(roleDto.getRoleName()));*/

        return userRole;
    }
}
