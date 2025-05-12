package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.DoctorAppointmentDto;
import com.example.Appointment.Booking.System.model.dto.LabTestDto;
import com.example.Appointment.Booking.System.model.dto.LoginRequestDto;
import com.example.Appointment.Booking.System.model.dto.MUserDto;
import com.example.Appointment.Booking.System.model.entity.Lab;
import com.example.Appointment.Booking.System.model.mapper.DoctorMapper;
import com.example.Appointment.Booking.System.model.mapper.LabTestMapper;
import com.example.Appointment.Booking.System.model.mapper.MUserMapper;
import com.example.Appointment.Booking.System.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestThymeleaf {
    private final UserService userService;
    private final MUserMapper MUserMapper;
    private final LabService labService;
    private final TestTypeService testTypeService;
    private final LabTestMapper labTestMapper;
    private final LabTestService labTestService;
    private final DoctorService doctorService;
    private final DoctorAppointmentService doctorAppointmentService;

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
    // Registration page দেখাবে
    @GetMapping("/appointment/confirm/book/{id}")
    public String confirmAppointment(Model model,@PathVariable("id") Long id) {
        model.addAttribute("confirm", new DoctorAppointmentDto());
        return "appointment";
    }
    @PostMapping("/appointment/confirm/book/{id}")
    public String confirmAppointmentSave(Model model,@PathVariable("id") Long id) {
        DoctorAppointmentDto doctorAppointmentDto = new DoctorAppointmentDto();
        doctorAppointmentDto.setDoctorId(id);
        doctorAppointmentDto.setPatientId(1L);// here get value from context holder
        model.addAttribute("confirm",doctorAppointmentDto);
        return "redirect:/test/user_dashboard";
    }
    @GetMapping("/user_profile")
    public String showUserProfile(Model model) {
        //model.addAttribute("user_profile", new MUserDto());
        return "profile";
    }
    @GetMapping("/upload_new_test")
    public String uploadNewTest(Model model){
        model.addAttribute("labTestDto", new LabTestDto());

        // load lab details
        List<Lab> labList = labService.getAllLabs();
        model.addAttribute("labList", labList);

        List<String> testTypeList = testTypeService.getAllTestTypesName();
        model.addAttribute("testTypeList", testTypeList);
        return "TestUpload";
    }
    @PostMapping("/lab-tests/save")
    public String saveLabTest(@ModelAttribute LabTestDto labTestDto) {
        try {
            labTestService.uploadLabTest(labTestMapper.mapToEntity(labTestDto));
        }catch (Exception e){
            System.out.println("Exception in save lab test = "+e.getMessage());
        }
        return "redirect:/test/upload_new_test?success"; // success indication
    }
    @GetMapping("/user_dashboard")
    public String userDashboard(Model model){
        model.addAttribute("doctors",doctorService.getAllDoctors());
        model.addAttribute("appointmentHistory",doctorAppointmentService.getHistory(1L));
        return "DashBoard";
    }

}
