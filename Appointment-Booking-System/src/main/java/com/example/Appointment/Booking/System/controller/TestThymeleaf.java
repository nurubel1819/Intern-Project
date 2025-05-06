package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.PatientDto;
import com.example.Appointment.Booking.System.model.mapper.PatientMapper;
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
    private final PatientMapper patientMapper;

    // Registration page দেখাবে
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new PatientDto());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") PatientDto patientDto) {
        try {
            userService.saveNewUser(patientMapper.mapToEntity(patientDto));
        } catch (Exception e) {
            e.printStackTrace(); // কোন error ঘটছে সেটা দেখাবে
        }
        return "redirect:/login";
    }

}
