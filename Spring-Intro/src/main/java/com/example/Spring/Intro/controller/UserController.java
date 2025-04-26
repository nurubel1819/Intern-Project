package com.example.Spring.Intro.controller;

import com.example.Spring.Intro.service.RoleService;
import com.example.Spring.Intro.service.UserService;
import com.example.Spring.Intro.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping("/add")
    private ResponseEntity<String> addUser(@RequestBody UserDto userDto)
    {
        return ResponseEntity.ok(userService.addUser(userDto));
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
