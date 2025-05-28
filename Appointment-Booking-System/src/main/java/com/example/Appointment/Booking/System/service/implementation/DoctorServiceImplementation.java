package com.example.Appointment.Booking.System.service.implementation;

import com.example.Appointment.Booking.System.model.dto.DoctorAvailabilityDto;
import com.example.Appointment.Booking.System.model.entity.*;
import com.example.Appointment.Booking.System.repository.AppointmentSlotRepository;
import com.example.Appointment.Booking.System.repository.DoctorAvailabilityRepository;
import com.example.Appointment.Booking.System.repository.DoctorRepository;
import com.example.Appointment.Booking.System.service.DoctorService;
import com.example.Appointment.Booking.System.service.RoleService;
import com.example.Appointment.Booking.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImplementation implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final AppointmentSlotRepository appointmentSlotRepository;

    @Override
    public Doctor uploadDoctor(Doctor doctor){

        try {
            Doctor saveDoctor = doctorRepository.save(doctor);
            MUser user = userService.getUserByPhone(doctor.getPhone());
            UserRole role = roleService.findRoleByName("DOCTOR");
            if(role==null){
                roleService.addNewRole("DOCTOR");
                role = roleService.findRoleByName("DOCTOR");
            }
            roleService.setUserRole(user.getId(),role.getId());
            return saveDoctor;
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return null;
        }
    }
    @Override
    public Doctor getByPhonNumber(String phonNumber){
        try {
            return doctorRepository.findByPhone(phonNumber);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public List<Doctor> getDoctorByNameLike(String name){
        try {
            return doctorRepository.findByNameContaining(name);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }
    @Override
    public Doctor findDoctorById(Long id){
        try {
            return doctorRepository.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Doctor updateDoctorDetails(Doctor doctor) {
        try {
            return doctorRepository.save(doctor);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean setDoctorAvailability(DoctorAvailabilityDto dto) {
        try {
            DoctorAvailability availability = new DoctorAvailability();
            availability.setDoctorId(dto.getDoctorId());
            availability.setDate(dto.getDate());
            availability.setAvailable(true);
            doctorAvailabilityRepository.save(availability);

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

                appointmentSlotRepository.save(slot);
            }
            return true;
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return false;
        }
    }
}
