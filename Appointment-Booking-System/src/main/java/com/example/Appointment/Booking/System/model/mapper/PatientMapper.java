package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.PatientDto;
import com.example.Appointment.Booking.System.model.entity.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatientMapper {

    public PatientDto mapToDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setPhonNumber(patient.getPhonNumber());
        patientDto.setPassword(patient.getPassword());
        patientDto.setEmail(patient.getEmail());
        patientDto.setGender(patient.getGender());
        patientDto.setDateOfBirth(patient.getDateOfBirth());
        return patientDto;
    }

    public Patient mapToEntity(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        patient.setPhonNumber(patientDto.getPhonNumber());
        patient.setPassword(patientDto.getPassword());
        patient.setEmail(patientDto.getEmail());
        patient.setGender(patientDto.getGender());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        return patient;
    }
}
