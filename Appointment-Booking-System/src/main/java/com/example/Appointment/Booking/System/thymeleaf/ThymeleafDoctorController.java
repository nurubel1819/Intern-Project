package com.example.Appointment.Booking.System.thymeleaf;

import com.example.Appointment.Booking.System.jwt.JwtUtils;
import com.example.Appointment.Booking.System.model.dto.DoctorAvailabilityDto;
import com.example.Appointment.Booking.System.model.entity.AppointmentSlot;
import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.model.entity.DoctorAvailability;
import com.example.Appointment.Booking.System.repository.AppointmentSlotRepository;
import com.example.Appointment.Booking.System.repository.DoctorAvailabilityRepository;
import com.example.Appointment.Booking.System.service.DoctorService;
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

    @RequestMapping("/panel")
    public String doctorPanel(Model model, HttpServletRequest request){
        String token = jwtUtils.getJwtFromCookies(request);
        Long id = jwtUtils.extractUserId(token);
        Doctor doctor = doctorService.findDoctorById(id);
        String doctorName = doctor.getName();
        model.addAttribute("doctorName",doctorName);
        return "DoctorPage";
    }

    @GetMapping("/set-availability") //--------------------Doctor Set Availability--------------------
    public String setAvailability(Model model,HttpServletRequest request){
        try {
            String token = jwtUtils.getJwtFromCookies(request);
            Long id = jwtUtils.extractUserId(token);
            DoctorAvailabilityDto dto = new DoctorAvailabilityDto();
            dto.setDoctorId(id);
            model.addAttribute("doctorAvailabilityDto", dto);

            Doctor doctor = doctorService.findDoctorById(id);
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
        try {
            DoctorAvailability availability = new DoctorAvailability();
            availability.setDoctorId(dto.getDoctorId());
            availability.setDate(dto.getDate());
            availability.setAvailable(true);
            doctorAvailabilityRepository.save(availability);

            // Start time: 4:00 PM
            LocalTime startTime = LocalTime.of(16, 0); // 16:00 means 4:00 PM

            int totalSlots = 20;
            int slotDurationInMinutes = 15;

            for (int i = 0; i < totalSlots; i++) {
                AppointmentSlot slot = new AppointmentSlot();
                slot.setDoctorId(dto.getDoctorId());
                slot.setDate(dto.getDate());

                // Start and end time per slot
                LocalTime slotStartTime = startTime.plusMinutes(i * slotDurationInMinutes);
                LocalTime slotEndTime = slotStartTime.plusMinutes(slotDurationInMinutes);

                slot.setStartTime(slotStartTime);
                slot.setEndTime(slotEndTime);
                slot.setBooked(false); // slot initially not booked

                slotRepository.save(slot);
            }

            return "redirect:/doctor/panel?message=Availability set successfully";
        }catch (Exception e){
            System.out.println("Exception in doctor set-availability = "+e.getMessage());
            return "redirect:/doctor/set-availability?message=Error in setting availability";
        }
    }
}
