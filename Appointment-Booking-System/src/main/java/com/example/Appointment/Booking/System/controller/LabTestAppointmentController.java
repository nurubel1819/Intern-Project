package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.LabTestAppointmentDto;
import com.example.Appointment.Booking.System.model.entity.LabTestAppointment;
import com.example.Appointment.Booking.System.model.mapper.LabTestAppointmentMapper;
import com.example.Appointment.Booking.System.service.LabTestAppointmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lab-test-appointments")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class LabTestAppointmentController {

    private final LabTestAppointmentService labTestAppointmentService;
    private final LabTestAppointmentMapper labTestAppointmentMapper;

    @PostMapping("/book-new-appointment")
    private ResponseEntity<String> bookNewAppointment(LabTestAppointmentDto labTestAppointmentDto){
        System.out.println("inside book new appointment controller");
        System.out.println("labTestAppointmentDto = "+labTestAppointmentDto);
        LabTestAppointment labTestAppointment = labTestAppointmentMapper.mapToEntity(labTestAppointmentDto);
        System.out.println("labTestAppointment = "+labTestAppointment);
        try {
            LabTestAppointment appointment = labTestAppointmentService.bookNewAppointment(labTestAppointment);
            return ResponseEntity.ok("Appointment booked successfully Lab name = "+appointment.getLabName());
        }catch (Exception e){
            System.out.println("Exception lab test appointment book save = "+e.getMessage());
            return ResponseEntity.badRequest().body("Appointment booked unsuccessfully");
        }
    }
}
