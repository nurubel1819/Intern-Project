package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.LabDto;
import com.example.Appointment.Booking.System.model.entity.Lab;
import org.springframework.stereotype.Component;

@Component
public class LabMapper {
    public Lab mapToEntity(LabDto labDto)
    {
        Lab lab = new Lab();
        lab.setLabName(labDto.getLabName());
        lab.setAddress(labDto.getAddress());
        lab.setRating(labDto.getRating());
        return lab;
    }
    public LabDto mapToDto(Lab lab)
    {
        LabDto labDto = new LabDto();
        labDto.setId(lab.getId());
        labDto.setLabName(lab.getLabName());
        labDto.setAddress(lab.getAddress());
        labDto.setRating(lab.getRating());
        return labDto;
    }
}
