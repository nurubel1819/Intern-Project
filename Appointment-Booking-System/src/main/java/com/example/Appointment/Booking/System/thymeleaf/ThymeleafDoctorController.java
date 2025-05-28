package com.example.Appointment.Booking.System.thymeleaf;

import com.example.Appointment.Booking.System.jwt.JwtUtils;
import com.example.Appointment.Booking.System.model.dto.DoctorAvailabilityDto;
import com.example.Appointment.Booking.System.model.entity.AppointmentSlot;
import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.model.entity.DoctorAvailability;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.repository.AppointmentSlotRepository;
import com.example.Appointment.Booking.System.repository.DoctorAvailabilityRepository;
import com.example.Appointment.Booking.System.service.DoctorService;
import com.example.Appointment.Booking.System.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@Controller
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class ThymeleafDoctorController {

    private final JwtUtils jwtUtils;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final AppointmentSlotRepository slotRepository;
    private final DoctorService doctorService;
    private final UserService userService;

    @RequestMapping("/panel")
    public String doctorPanel(Model model, HttpServletRequest request){
        String token = jwtUtils.getJwtFromCookies(request);
        Long id = jwtUtils.extractUserId(token);
        MUser user = userService.getUserById(id);
        String doctorName = user.getName();
        model.addAttribute("doctorName",doctorName);
        return "DoctorPage";
    }

    @GetMapping("/set-availability") //--------------------Doctor Set Availability--------------------
    public String setAvailability(Model model,HttpServletRequest request){
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            Long id = jwtUtils.extractUserId(token);
            String phone = jwtUtils.extractUsername(token);
            DoctorAvailabilityDto dto = new DoctorAvailabilityDto();
            Long doctorId = doctorService.getByPhonNumber(phone).getId();
            dto.setDoctorId(doctorId);
            model.addAttribute("doctorAvailabilityDto", dto);

            Doctor doctor = doctorService.findDoctorById(doctorId);
            model.addAttribute("doctor", doctor);
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/doctor/set-availability?message=Error in setting availability";
        }
        return "DoctorAvailabilityPage";
    }

    @PostMapping("/set-availability")
    public String setAvailabilityConfirm(@ModelAttribute DoctorAvailabilityDto dto ){

        if(doctorAvailabilityRepository.existsByDoctorIdAndDate(dto.getDoctorId(),dto.getDate()))
            return "redirect:/doctor/set-availability?message=Your availability already exists for this date";
        if(doctorService.setDoctorAvailability(dto))
            return "redirect:/doctor/panel?message=Availability set successfully";
        else return "redirect:/doctor/set-availability?message=Error in setting availability";
    }
}
