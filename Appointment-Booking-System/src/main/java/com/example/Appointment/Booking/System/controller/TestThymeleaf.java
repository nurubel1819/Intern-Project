package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.LoginRequestDto;
import com.example.Appointment.Booking.System.model.dto.MUserDto;
import com.example.Appointment.Booking.System.model.mapper.MUserMapper;
import com.example.Appointment.Booking.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestThymeleaf {
    private final UserService userService;
    private final MUserMapper MUserMapper;

    // Registration page দেখাবে
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("MUser", new MUserDto());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("MUser") MUserDto MUserDto) {
        try {
            userService.saveNewUser(MUserMapper.mapToEntity(MUserDto));
        } catch (Exception e) {
            e.printStackTrace(); // কোন error ঘটছে সেটা দেখাবে
        }
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("login_request", new LoginRequestDto());
        return "login";
    }
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("login_request") LoginRequestDto loginRequestDto){
        System.out.println(loginRequestDto.getPassword()+" "+loginRequestDto.getPhone());
        return "redirect:/";
    }

}
