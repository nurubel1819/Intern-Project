package com.example.Spring.Intro.security;

import com.example.Spring.Intro.config.JwtService;
import com.example.Spring.Intro.model.dto.UserDto;
import com.example.Spring.Intro.model.entity.Role;
import com.example.Spring.Intro.model.entity.User;
import com.example.Spring.Intro.repository.RoleRepo;
import com.example.Spring.Intro.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        Role newRole = new Role();
        newRole.setRoleName("USER");
        user.setRoles(Set.of(newRole));


        try {
            userRepo.save(user);
            return ResponseEntity.ok("Registration successful JWT = "+jwtService.generateToken(user));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        UserDetails userDetails = userRepo.findByName(userDto.getUsername());
        String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(Collections.singletonMap("token", jwt));
    }
}

