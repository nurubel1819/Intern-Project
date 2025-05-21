package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.LabTestAppointmentDto;
import com.example.Appointment.Booking.System.model.entity.LabTest;
import com.example.Appointment.Booking.System.model.entity.LabTestAppointment;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.service.LabTestService;
import com.example.Appointment.Booking.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class LabTestAppointmentMapper {

    private final LabTestService labTestService;
    private final UserService userService;

    public LabTestAppointment mapToEntity(LabTestAppointmentDto dto)
    {
        LabTestAppointment labTestAppointment = new LabTestAppointment();
        labTestAppointment.setBookingDate(dto.getBookingDate());
        labTestAppointment.setNote(dto.getNote());

        try {
            LabTest labTest = labTestService.getLabTestByName(dto.getTestName());
            labTestAppointment.setLabTest(labTest);
            var testDuration = labTest.getDurationInHours().toDays();
            labTestAppointment.setDeliveryDate(dto.getBookingDate().plusDays(testDuration));

            labTestAppointment.setLabName(dto.getLabName()); // selected lab set this lab name
            MUser user = userService.getUserById(dto.getUserId());
            labTestAppointment.setUser(user);
            return labTestAppointment;
        }catch (Exception e){
            System.out.println("Exception in LabTest Appointment Mapper error = "+e.getMessage());
            return null;
        }
    }
}
