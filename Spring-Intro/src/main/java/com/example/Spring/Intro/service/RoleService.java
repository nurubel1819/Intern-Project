package com.example.Spring.Intro.service;

import com.example.Spring.Intro.model.dto.RoleDto;
import com.example.Spring.Intro.model.entity.User;
import com.example.Spring.Intro.model.entity.UserRole;
import com.example.Spring.Intro.repository.RoleRepo;
import com.example.Spring.Intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    public String AddRole(RoleDto roleDto)
    {
        UserRole userRole = new UserRole();
        userRole.setRoleName(roleDto.getRoleName());
        userRole.setDescription(roleDto.getDescription());

       try {
           //find role user and add to role user set
           List<User> roleUser = userRepo.findAllByIdIn(roleDto.getUserIds());
           Set<User> someRoleUser = new HashSet<>(roleUser);
           userRole.setUser(someRoleUser);
           UserRole save_role = roleRepo.save(userRole);
           return "Role added successfully"+" Role Id : "+save_role.getId()+" Role Name : "+save_role.getRoleName();
       }
       catch (Exception e)
       {
           return "Role not added"+" Exception : "+e.getMessage();
       }
    }

    public String DeleteRole(RoleDto roleDto)
    {
        Long id = roleDto.getId();
        if(roleRepo.existsById(id))
        {
            roleRepo.deleteById(id);
            return "Role deleted successfully";
        }
        else return "Role can't exist in database";
    }

    public String UpdateRole(RoleDto roleDto)
    {
        UserRole userRole = new UserRole();
        userRole.setId(roleDto.getId());
        userRole.setRoleName(roleDto.getRoleName());
        userRole.setDescription(roleDto.getDescription());

        try {
            //find role user and add to role user set
            List<User> roleUser = userRepo.findAllByIdIn(roleDto.getUserIds());
            Set<User> someRoleUser = new HashSet<>(roleUser);
            userRole.setUser(someRoleUser);
            UserRole save_role = roleRepo.save(userRole);
            return "Role added successfully"+" Role Id : "+save_role.getId()+" Role Name : "+save_role.getRoleName();
        }
        catch (Exception e)
        {
            return "Role not added"+" Exception : "+e.getMessage();
        }
    }

    public String GetRole(RoleDto roleDto)
    {
        Long id = roleDto.getId();
        if(roleRepo.existsById(id))
        {
            UserRole role = roleRepo.findById(id).get();
            return "Role Id : "+role.getId()+" Role Name : "+role.getRoleName()+" Description : "+role.getDescription();
        }
        else return "Role can't exist in database";
    }

}
