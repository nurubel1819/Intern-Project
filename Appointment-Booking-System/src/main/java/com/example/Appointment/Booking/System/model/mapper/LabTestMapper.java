package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.LabTestDto;
import com.example.Appointment.Booking.System.model.entity.LabTest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LabTestMapper {

    public LabTest mapToEntity(LabTestDto dto)
    {
        LabTest labTest = new LabTest();
        return labTest;
    }
}
