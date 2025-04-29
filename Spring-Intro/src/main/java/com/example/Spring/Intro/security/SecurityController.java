package com.example.Spring.Intro.security;

import com.example.Basic.Authentication.repo.UserDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {

    private final UserDetailsRepo userDetailsRepo;

    @GetMapping("/test")
    private String securityTest()
    {
        return "hello";
    }
}
