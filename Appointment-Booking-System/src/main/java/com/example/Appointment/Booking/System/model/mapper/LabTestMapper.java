package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.LabTestDto;
import com.example.Appointment.Booking.System.model.entity.Lab;
import com.example.Appointment.Booking.System.model.entity.LabTest;
import com.example.Appointment.Booking.System.model.entity.TestType;
import com.example.Appointment.Booking.System.repository.TestTypeRepository;
import com.example.Appointment.Booking.System.service.LabService;
import com.example.Appointment.Booking.System.service.LabTestService;
import com.example.Appointment.Booking.System.service.TestTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class LabTestMapper {

    private final LabTestService labTestService;
    private final TestTypeService testTypeService;

    public LabTest mapToEntity(LabTestDto dto)
    {
        LabTest labTest;
        if(labTestService.getLabTestByName(dto.getTestName()) != null)
            labTest = labTestService.getLabTestByName(dto.getTestName());
        else labTest = new LabTest();
        labTest.setTestName(dto.getTestName());
        labTest.setPrice(dto.getPrice());
        labTest.setDescription(dto.getDescription());
        labTest.setDurationInHours(Duration.ofHours(dto.getDurationInHours()));
        TestType testType = testTypeService.getTestTypeByName(dto.getTestType());
        labTest.setTestType(testType);
        return labTest;
    }
}
