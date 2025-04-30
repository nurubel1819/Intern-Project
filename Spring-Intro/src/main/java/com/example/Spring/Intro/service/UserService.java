package com.example.Spring.Intro.service;

import com.example.Spring.Intro.model.dto.RoleDto;
import com.example.Spring.Intro.model.dto.UserDto;
import com.example.Spring.Intro.model.dto.UserRoleDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    public String addUser(UserDto userDto);
    public String delete_user(Long id);
    public List<UserDto> all_users();
    public String updateUser(Long id, UserDto userDto);
    public UserDto getUserById(Long id);
    public String registerUser(String username, String password);
    public UserRoleDto getRoles(Long userId);
}
