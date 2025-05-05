package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.UserDto;
import com.example.Appointment.Booking.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestThymeleaf {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("UserRegistrationUi", new UserDto());
        return "UserRegistrationUi"; // এই নামটাই html ফাইলের নাম (registration.html)
    }

    /*@PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDto userDto) {
        // এখানে ইউজার সেভ করার লজিক থাকবে
        System.out.println("User registered: " + userDto);
        return "redirect:/login"; // রেজিস্ট্রেশনের পরে login পেইজে রিডাইরেক্ট
    }*/

}
