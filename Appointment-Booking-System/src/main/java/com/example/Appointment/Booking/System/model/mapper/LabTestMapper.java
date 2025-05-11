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

    private final LabService labService;
    private final LabTestService labTestService;
    private final TestTypeService testTypeService;
    private final TestTypeRepository testTypeRepository;

    public LabTest mapToEntity(LabTestDto dto)
    {
        LabTest labTest = new LabTest();
        labTest.setTestName(dto.getTestName());
        labTest.setPrice(dto.getPrice());
        labTest.setDescription(dto.getDescription());
        try {

            Lab lab = labService.getLabDetails(dto.getLabName());
            Set<Lab> labs;
            if(labTestService.getLabTestByName(dto.getTestName()) != null)
            {
                LabTest test = labTestService.getLabTestByName(dto.getTestName());
                labs = test.getLabs();
                labs.add(lab);
                labTest.setLabs(labs);
            }
            else labTest.setLabs(Set.of(lab));
            TestType testType = testTypeService.getTestTypeByName(dto.getTestType());
            labTest.setTestType(testType);
            labTest.setDurationInHours(Duration.ofHours(dto.getDurationInHours()));
            //System.out.println("Duration = "+labTest.getDurationInHours().toHours()+" hours" ); //it user for test to show duration in hour

        }catch (Exception e)
        {
            System.out.println("Exception in LabTest Mapper error = "+e.getMessage());
            //return null;
        }

        return labTest;
    }
}
