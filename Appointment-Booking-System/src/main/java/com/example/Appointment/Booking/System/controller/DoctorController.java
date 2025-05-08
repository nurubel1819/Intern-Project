package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.DoctorAvailabilityDto;
import com.example.Appointment.Booking.System.model.dto.DoctorDto;
import com.example.Appointment.Booking.System.model.entity.CountAppointment;
import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.model.mapper.DoctorMapper;
import com.example.Appointment.Booking.System.repository.CountAppointmentRepository;
import com.example.Appointment.Booking.System.service.DoctorService;
import com.example.Appointment.Booking.System.validation.ImportantValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final CountAppointmentRepository countAppointmentRepository;

    @PostMapping("/registration")
    private ResponseEntity<DoctorDto> registration(DoctorDto doctorDto){
        if(ImportantValidation.isValidBDPhone(doctorDto.getPhone()))
        {
            String phonNumber = doctorDto.getPhone();
            if(phonNumber.length() > 11) phonNumber = phonNumber.substring(3);
            doctorDto.setPhone(phonNumber);

            Doctor doctor = doctorMapper.mapToEntity(doctorDto);
            if(doctorDto.getEmail()!=null && ImportantValidation.isValidEmail(doctorDto.getEmail()))
            {
                return ResponseEntity.ok(doctorMapper.mapToDto(doctorService.uploadDoctor(doctor)));
            }
            else if(doctorDto.getEmail()==null)
                return ResponseEntity.ok(doctorMapper.mapToDto(doctorService.uploadDoctor(doctor)));
            else return ResponseEntity.badRequest().body(null);
        }
        else return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/set_appointment_number/doctor_id={id}")
    private ResponseEntity<String> setAppointmentNumber(@PathVariable("id") Long id){
        Doctor doctor = doctorService.findDoctorById(id);
        if(doctor==null) return ResponseEntity.ok("doctor not found");
        else
        {
            CountAppointment countAppointment = new CountAppointment();
            countAppointment.setDoctorId(doctor.getId());
            countAppointment.setTotalPatient(10);
            try {
                countAppointmentRepository.save(countAppointment);
                return ResponseEntity.ok("set appointment number successfully");
            }catch (Exception e){
                return ResponseEntity.ok("set appointment number failed");
            }
        }
    }

    @PostMapping("/set_appointment_status")
    private ResponseEntity<String> setAppointmentStatus(@RequestBody DoctorAvailabilityDto dto){
        return ResponseEntity.ok(doctorService.setAppointment(dto.getDoctorId(),dto.getTotalPossibilityPatient()));
    }

}
