package com.example.Spring.Intro.service;

import com.example.Spring.Intro.model.dto.RoleDto;
import com.example.Spring.Intro.model.entity.User;
import com.example.Spring.Intro.model.entity.Role;
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
        Role role = new Role();
        role.setRoleName(roleDto.getRoleName());
        role.setDescription(roleDto.getDescription());
        try {
            Role save_role = roleRepo.save(role);
            return "Role added successfully"+" Role Id : "+save_role.getId()+" Role Name : "+save_role.getRoleName();
        }catch (Exception e)
        {
            return "Role not added"+" Exception : "+e.getMessage();
        }
    }

    public String SetRole(RoleDto roleDto)
    {
        try {
            Role role = roleRepo.findByRoleName(roleDto.getRoleName());
            if (role == null) {
                return "Role not found!";
            }

            List<User> roleUser = userRepo.findAllByIdIn(roleDto.getUserIds());

            Set<User> updatedUserSet = role.getUsers();
            if (updatedUserSet == null) {
                updatedUserSet = new HashSet<>();
            }
            updatedUserSet.addAll(roleUser);
            role.setUsers(updatedUserSet);

            Role save_role = roleRepo.save(role);

            return "Role set successfully"+" Role Id : "+save_role.getId()+" User Id : "+save_role.getUsers().stream().map(User::getId).toList();
        }
        catch (Exception e)
        {
            return "Role not added"+" Exception : "+e.getMessage();
        }
    }

    public String DeleteRole(Long roleId)
    {
        if(roleRepo.existsById(roleId))
        {
            roleRepo.deleteById(roleId);
            return "Role deleted successfully";
        }
        else return "Role can't exist in database";
    }

    public String UpdateRole(RoleDto roleDto)
    {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setRoleName(roleDto.getRoleName());
        role.setDescription(roleDto.getDescription());

        try {
            //find role user and add to role user set
            List<User> roleUser = userRepo.findAllByIdIn(roleDto.getUserIds());
            Set<User> someRoleUser = new HashSet<>(roleUser);
            role.setUsers(someRoleUser);
            Role save_role = roleRepo.save(role);
            return "Role added successfully"+" Role Id : "+save_role.getId()+" Role Name : "+save_role.getRoleName();
        }
        catch (Exception e)
        {
            return "Role not added"+" Exception : "+e.getMessage();
        }
    }

    public String GetRole(Long id)
    {
        if(roleRepo.existsById(id))
        {
            Role role = roleRepo.findById(id).get();
            return "Role Id : "+role.getId()+" Role Name : "+role.getRoleName()+" Description : "+role.getDescription();
        }
        else return "Role can't exist in database";
    }

    public boolean accessAuthority(Long userId, String roleName) {
        //return userRepo.findById(userId).map(user -> user.getRoles().stream().anyMatch(role -> role.getRoleName().equalsIgnoreCase(roleName))).orElse(false);
        return userRepo.findById(userId)
                .map(user -> user.getRoles().stream()
                        .anyMatch(role ->
                                role.getRoleName() != null &&
                                        role.getRoleName().equalsIgnoreCase(roleName)
                        )
                )
                .orElse(false);
    }

}
