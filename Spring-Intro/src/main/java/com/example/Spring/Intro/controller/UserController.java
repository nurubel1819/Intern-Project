package com.example.Spring.Intro.controller;

import com.example.Spring.Intro.model.dto.UserRoleDto;
import com.example.Spring.Intro.model.entity.User;
import com.example.Spring.Intro.repository.UserRepo;
import com.example.Spring.Intro.security.JwtService;
import com.example.Spring.Intro.service.RoleService;
import com.example.Spring.Intro.service.UserService;
import com.example.Spring.Intro.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserRepo userRepo;

    @GetMapping("/form")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "UserWeb";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute UserDto user, Model model) {
        userService.addUser(user);
        model.addAttribute("user", user);
        return "UserWeb"; // HTML পেইজটির নাম
    }

    @PostMapping("/find")
    public String findUser(@ModelAttribute UserDto user, Model model) {
        User foundUser = userRepo.findById(user.getId()).orElse(null);
        user.setUsername(foundUser.getName());
        user.setPassword(foundUser.getPassword());
        model.addAttribute("user", user);
        return "UserWeb";
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute UserDto user, Model model) {
        if(userRepo.existsById(user.getId()))
        {
            userRepo.deleteById(user.getId());
        }
        model.addAttribute("user", new UserDto()); // ফাঁকা ফর্ম দেখাতে
        return "user-details";
    }

    @PatchMapping("/update")
    private ResponseEntity<String> updateUser(@RequestBody UserDto userDto)
    {
        if(roleService.accessAuthority(userDto.getId(),"USER")
        || roleService.accessAuthority(userDto.getId(),"ADMIN"))
        {
            return ResponseEntity.ok(userService.updateUser(userDto.getId(), userDto));
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete/id={id}")
    private ResponseEntity<String> deleteUser(@PathVariable("id") Long id)
    {
        if(roleService.accessAuthority(id,"ADMIN")
        || roleService.accessAuthority(id,"USER"))
        {
            return ResponseEntity.ok(userService.delete_user(id));
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);
    }
    @GetMapping("/get/id={id}")
    private ResponseEntity<UserDto> getUser(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok(userService.getUserById(id));
    }




}
