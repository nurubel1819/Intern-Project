package com.example.Spring.Intro.controller;


import com.example.Spring.Intro.model.dto.RoleDto;
import com.example.Spring.Intro.model.dto.SetSingleUserRoleDto;
import com.example.Spring.Intro.model.dto.UserDto;
import com.example.Spring.Intro.model.dto.UserRoleDto;
import com.example.Spring.Intro.model.mapper.UserMapper;
import com.example.Spring.Intro.service.RoleService;
import com.example.Spring.Intro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.*;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class BasicController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @GetMapping("/role_page")
    public String showSignUpForm(Model model) {
        model.addAttribute("userForm", new UserRoleDto());
        return "RoleSee";
    }

    @PostMapping("/seeRoles")
    public String seeRoles(@ModelAttribute UserRoleDto userDto, Model model) {
        userDto = userService.getRoles(userDto.getUserId());
        Set<String> roles = userDto.getRoleName();
        model.addAttribute("presentRoles", roles);
        model.addAttribute("userForm", userDto); // Preserve the form state
        return "RoleSee";
    }

    @PostMapping("/setRole")
    public String setRole(@ModelAttribute SetSingleUserRoleDto userDto, Model model) {
        RoleDto roleDto = new RoleDto();
        roleDto.setUserIds(List.of(userDto.getUserId()));
        roleDto.setRoleName(userDto.getRoleName());
        roleService.SetRole(roleDto);
        UserRoleDto user = userService.getRoles(userDto.getUserId());
        Set<String> roles = user.getRoleName();
        model.addAttribute("presentRoles", roles);
        model.addAttribute("userForm", userDto);
        return "RoleSee";
    }


}
