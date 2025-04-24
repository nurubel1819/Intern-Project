package com.example.Spring.Intro.controller;

import com.example.Spring.Intro.service.RoleService;
import com.example.Spring.Intro.service.UserService;
import com.example.Spring.Intro.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping("/add")
    private ResponseEntity<String> add_user(@RequestBody UserDto userDto)
    {
        if(roleService.access_authority(userDto.getId(),"ADMIN")
        || roleService.access_authority(userDto.getId(),"USER"))
        {
            return ResponseEntity.ok(userService.add_user(userDto));
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);

    }

    @PutMapping("/update")
    private ResponseEntity<String> update_user(@RequestBody UserDto userDto)
    {
        if(roleService.access_authority(userDto.getId(),"USER")
        || roleService.access_authority(userDto.getId(),"ADMIN"))
        {
            return ResponseEntity.ok(userService.update_user(userDto.getId(), userDto));
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete")
    private ResponseEntity<String> delete_user(UserDto userDto)
    {
        if(roleService.access_authority(userDto.getId(),"ADMIN")
        || roleService.access_authority(userDto.getId(),"USER"))
        {
            return ResponseEntity.ok(userService.delete_user(userDto.getId()));
        }
        else return new ResponseEntity<>("You don't have access to create blog", HttpStatus.FORBIDDEN);
    }
    @GetMapping("/get")
    private ResponseEntity<UserDto> get_user(UserDto userDto)
    {
        return ResponseEntity.ok(userService.getUserById(userDto.getId()));
    }




}
