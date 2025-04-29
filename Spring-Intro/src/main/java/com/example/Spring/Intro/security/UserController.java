package com.example.Spring.Intro.security;

import com.example.Basic.Authentication.dto.UserDto;
import com.example.Basic.Authentication.entity.User;
import com.example.Basic.Authentication.repo.UserDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsRepo userDetailsRepo;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/upload_user")
    private ResponseEntity<String> uploadUser(@RequestBody UserDto userDto)
    {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        try {
            userDetailsRepo.save(user);
            return ResponseEntity.ok().body("User saved successfully");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("User not saved");
        }
    }

    @GetMapping("set_user={id}")
    private ResponseEntity<String> getUser(@PathVariable("id") Long id)
    {
        User user = userDetailsRepo.findById(id).orElseThrow();
        return ResponseEntity.ok().body(user.toString());
    }
}
