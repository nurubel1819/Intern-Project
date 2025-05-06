package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.TestTypeDto;
import com.example.Appointment.Booking.System.model.entity.TestType;
import org.springframework.stereotype.Component;

@Component
public class TestTypeMapper {
    public TestType mapToEntity(TestTypeDto testTypeDto)
    {
        TestType testType = new TestType();
        testType.setName(testTypeDto.getName());
        testType.setDescription(testTypeDto.getDescription());
        return testType;
    }
    public TestTypeDto mapToDto(TestType testType)
    {
        TestTypeDto testTypeDto = new TestTypeDto();
        testTypeDto.setId(testType.getId());
        testTypeDto.setName(testType.getName());
        testTypeDto.setDescription(testType.getDescription());
        return testTypeDto;
    }
}
