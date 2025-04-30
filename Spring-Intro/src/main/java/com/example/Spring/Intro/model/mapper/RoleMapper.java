package com.example.Spring.Intro.model.mapper;

import com.example.Spring.Intro.model.dto.RoleDto;
import com.example.Spring.Intro.model.entity.Role;
import com.example.Spring.Intro.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final RoleRepo roleRepo;

    public Role mapToUserRole(RoleDto roleDto)
    {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setRoleName(roleDto.getRoleName());
        role.setDescription(roleDto.getDescription());

        return role;
    }
}
