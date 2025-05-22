package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.*;
import com.example.Appointment.Booking.System.model.entity.*;
import com.example.Appointment.Booking.System.model.mapper.LabTestAppointmentMapper;
import com.example.Appointment.Booking.System.model.mapper.MUserMapper;
import com.example.Appointment.Booking.System.repository.AppointmentSlotRepository;
import com.example.Appointment.Booking.System.repository.DoctorAvailabilityRepository;
import com.example.Appointment.Booking.System.repository.RoleRepository;
import com.example.Appointment.Booking.System.service.AuthenticationService;
import com.example.Appointment.Booking.System.service.LabTestAppointmentService;
import com.example.Appointment.Booking.System.service.UserService;
import com.example.Appointment.Booking.System.validation.ImportantValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final MUserMapper MUserMapper;
    private final RoleRepository roleRepository;
    private final LabTestAppointmentMapper labTestAppointmentMapper;
    private final LabTestAppointmentService labTestAppointmentService;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final AppointmentSlotRepository slotRepository;

    @PostMapping("/registration")
    private ResponseEntity<?> registration(MUserDto userDto){
        if(ImportantValidation.isValidBDPhone(userDto.getPhonNumber()))
        {
            if(!userDto.getPassword().equals(userDto.getConfirmPassword()))
                return ResponseEntity.badRequest().body(Map.of("message","password and confirm password not match"));
            // remove +88 from BD phone number
            String phonNumber = userDto.getPhonNumber();
            if(phonNumber.length() > 11) phonNumber = phonNumber.substring(3);
            userDto.setPhonNumber(phonNumber);

            MUser user = MUserMapper.mapToEntity(userDto);
            System.out.println("user = "+user);
            if(userDto.getEmail() != null && ImportantValidation.isValidEmail(userDto.getEmail()))
            {

                UserRole userRole = roleRepository.findByRole("USER");
                if(userRole==null)
                {
                    userRole = new UserRole();
                    userRole.setRole("USER");
                }
                Set<MUser> users = userRole.getUsers();
                users.add(user);
                userRole.setUsers(users);

                user.setUserRoles(Set.of(userRole));

                try {
                    return ResponseEntity.ok(MUserMapper.mapToDto(authenticationService.sinUp(user)));
                }catch (Exception e){
                    System.out.println("Exception = "+e.getMessage());
                    return ResponseEntity.badRequest().body(Map.of("message","Phone or email already exists in database"));
                }
            }
            else if(userDto.getEmail() == null) return ResponseEntity.ok(MUserMapper.mapToDto(authenticationService.sinUp(user)));
            else return ResponseEntity.badRequest().body(Map.of("message","invalid email id"));
        }
        else return ResponseEntity.badRequest().body(Map.of("message","invalid phone number"));
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody SignInRequestDto signInRequestDto){
        if(ImportantValidation.isValidBDPhone(signInRequestDto.getPhone()))
        {
            String phonNumber = signInRequestDto.getPhone();
            if(phonNumber.length() > 11) phonNumber = phonNumber.substring(3);
            signInRequestDto.setPhone(phonNumber);
            JwtAuthenticationResponseDto responseDto = authenticationService.signIn(signInRequestDto);
            if(responseDto.getToken() != null) return ResponseEntity.ok(responseDto);
            else return ResponseEntity.badRequest().body(Map.of("message","invalid phone number or password"));
        }
        else return ResponseEntity.badRequest().body(Map.of("message","invalid phone number"));
    }

    @PostMapping("/doctor_appointment")
    private ResponseEntity<String> doctorAppointment(@RequestBody DoctorAppointmentDto doctorAppointmentDto){
        return ResponseEntity.ok(userService.bookDoctor(doctorAppointmentDto.getDoctorId(),doctorAppointmentDto.getPatientId()));

    }

    @PostMapping("/lab-test-appointment")
    private ResponseEntity<String> bookNewAppointment(LabTestAppointmentDto labTestAppointmentDto){
        System.out.println("labTestAppointmentDto = "+labTestAppointmentDto);
        LabTestAppointment labTestAppointment = labTestAppointmentMapper.mapToEntity(labTestAppointmentDto);
        System.out.println("labTestAppointment = "+labTestAppointment);
        try {
            labTestAppointmentService.bookNewAppointment(labTestAppointment);
            return ResponseEntity.ok("Appointment booked successfully");
        }catch (Exception e){
            System.out.println("Exception lab test appointment book save = "+e.getMessage());
            return ResponseEntity.badRequest().body("Lab test appointment not booked");
        }
    }
    //for testing
    @GetMapping("/get_user_role={id}")
    private ResponseEntity<Set<String>> getUserRole(@PathVariable("id") Long id){
        try {
            MUser user = userService.getUserById(id);
            Set<String> roles = new HashSet<>();
            for (UserRole userRole : user.getUserRoles()) {
                roles.add(userRole.getRole());
            }
            return ResponseEntity.ok(roles);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Set.of("User not found"));
        }
    }

    /*@GetMapping("/slots/{doctorId}")
    public ResponseEntity<?> getAvailableSlots(@PathVariable Long doctorId,
                                               @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);

        // Step 1: Check if doctor is available that day
        Optional<DoctorAvailability> availability = doctorAvailabilityRepository
                .findByDoctorIdAndDate(doctorId, localDate);

        if (availability.isPresent() && !availability.get().isAvailable()) {
            return ResponseEntity.ok("Doctor is not available on this date.");
        }

        // Step 2: Load available slots
        List<AppointmentSlot> slots = slotRepository.findByDoctorIdAndDateAndBookedFalse(doctorId, localDate);
        return ResponseEntity.ok(slots);
    }*/
    @PostMapping("/book-slot/{slotId}")
    public ResponseEntity<String> bookSlot(@PathVariable Long slotId,
                                           @RequestParam Long patientId) {
        AppointmentSlot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        // Check doctor availability on that day
        Optional<DoctorAvailability> availability = doctorAvailabilityRepository
                .findByDoctorIdAndDate(slot.getDoctorId(), slot.getDate());

        if (availability.isPresent() && !availability.get().isAvailable()) {
            return ResponseEntity.badRequest().body("Doctor is not available on this date.");
        }

        if (slot.isBooked()) {
            return ResponseEntity.badRequest().body("Slot already booked!");
        }

        slot.setBooked(true);
        slot.setPatientId(patientId);
        slotRepository.save(slot);

        return ResponseEntity.ok("Slot booked successfully!");
    }
    /*@PostMapping("/doctorDateSave")
    public ResponseEntity<?> doctorDateSave(@RequestParam Long doctorId,@RequestParam LocalDate date){
        try {
            DoctorAvailability availability = new DoctorAvailability();
            availability.setDoctorId(doctorId);
            availability.setDate(date);
            availability.setAvailable(true);
            doctorAvailabilityRepository.save(availability);

            for(int i=0;i<3;i++){
                AppointmentSlot slot = new AppointmentSlot();
                slot.setDoctorId(doctorId);
                slot.setDate(date);
                slotRepository.save(slot);
            }
            return ResponseEntity.ok("Successfully saved");
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage()+"");
            return ResponseEntity.badRequest().body("Not saved = "+e.getMessage());
        }
    }*/


}
