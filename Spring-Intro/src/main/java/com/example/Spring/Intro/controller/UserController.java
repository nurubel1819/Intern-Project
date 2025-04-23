package com.example.Spring.Intro.controller;

import com.example.Spring.Intro.service.UserService;
import com.example.Spring.Intro.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public String add_user(@RequestBody UserDto userDto) {
        return userService.add_user(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete_user(@PathVariable Long id) {
        return userService.delete_user(id);
    }

    @GetMapping("/see_all")
    private ResponseEntity<List<UserDto>> see_all_users()
    {
        return ResponseEntity.ok(userService.all_users());
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<String> Update_user(@PathVariable Long id, @RequestBody UserDto userDto)
    {
        return ResponseEntity.ok(userService.update_user(id, userDto));
    }

}
