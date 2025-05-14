package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.JwtAuthenticationResponseDto;
import com.example.Appointment.Booking.System.model.dto.LoginRequestDto;
import com.example.Appointment.Booking.System.model.dto.MUserDto;
import com.example.Appointment.Booking.System.model.dto.SignInRequestDto;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
import com.example.Appointment.Booking.System.model.mapper.MUserMapper;
import com.example.Appointment.Booking.System.repository.RoleRepository;
import com.example.Appointment.Booking.System.service.*;
import com.example.Appointment.Booking.System.validation.ImportantValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class ThymeleafController {

    private final UserService userService;
    private final MUserMapper userMapper;
    private final DoctorService doctorService;
    private final LabTestService labTestService;
    private final DoctorAppointmentService doctorAppointmentService;
    private final AuthenticationService authenticationService;
    private final RoleRepository roleRepository;

    @GetMapping("/")
    public String homePage() {
        return "HomePage";
    }
    @GetMapping("/registration")      //-------------------------registration-----------------------
    public String showRegistrationForm(Model model) {
        model.addAttribute("MUser", new MUserDto());
        return "registration";
    }
    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("MUser") MUserDto userDto,Model model) {
        System.out.println("user = "+userDto);
        model.addAttribute("MUser", userDto);
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())) return "registration";
        else if(!ImportantValidation.isValidBDPhone(userDto.getPhonNumber())) return "registration";
        else if(userDto.getEmail()!=null && !ImportantValidation.isValidEmail(userDto.getEmail())) return "registration";
        else
        {
            try {
                MUser user = userMapper.mapToEntity(userDto);

                UserRole userRole = roleRepository.findByRole("USER");
                if(userRole==null)
                {
                    userRole = new UserRole();
                    userRole.setRole("USER");
                }
                Set<MUser> users = userRole.getUsers();
                users.add(user);
                userRole.setUsers(users);

                user.setUserRoles(Set.of(userRole));

                authenticationService.sinUp(user);
                return "redirect:/user_dashboard";

            }catch (Exception e){
                System.out.println("Exception = "+e.getMessage());
                return "registration";
            }
        }
    }
    @GetMapping("/login")         //-------------------------Login-------------------
    public String loginPage(Model model){
        model.addAttribute("login_request", new SignInRequestDto());
        return "login";
    }
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("login_request") SignInRequestDto signInRequestDto,Model model){


        System.out.println("User phone = "+signInRequestDto.getPhone() );
        System.out.println("User password = "+signInRequestDto.getPassword());

        // Authenticate
        JwtAuthenticationResponseDto status = authenticationService.signIn(signInRequestDto);
        if(status.getToken()!=null) return "redirect:/user_dashboard";
        model.addAttribute("login_request", signInRequestDto);
        model.addAttribute("errorMessage", "Invalid phone number or password");
        return "login";
    }
    @GetMapping("/user_dashboard")     //-------------------------User Dashboard ------------------------
    public String userDashboard(Model model){
        model.addAttribute("doctors",doctorService.getAllDoctors());
        model.addAttribute("allTest",labTestService.getAllLabTest());
        model.addAttribute("appointmentHistory",doctorAppointmentService.getHistory(1L));
        return "DashBoard";
    }
}
