package com.example.Spring.Intro.service;

import com.example.Spring.Intro.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    public String add_user(UserDto userDto);
    public String delete_user(Long id);
    public List<UserDto> all_users();
    public String update_user(Long id, UserDto userDto);
    public UserDto getUserById(Long id);
}
