package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.*;
import com.example.Appointment.Booking.System.model.entity.LabTestAppointment;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
import com.example.Appointment.Booking.System.model.mapper.LabTestAppointmentMapper;
import com.example.Appointment.Booking.System.model.mapper.MUserMapper;
import com.example.Appointment.Booking.System.repository.RoleRepository;
import com.example.Appointment.Booking.System.service.AuthenticationService;
import com.example.Appointment.Booking.System.service.LabTestAppointmentService;
import com.example.Appointment.Booking.System.service.UserService;
import com.example.Appointment.Booking.System.validation.ImportantValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

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

    @PostMapping("/registration")
    private ResponseEntity<MUserDto> registration(MUserDto userDto){
        if(ImportantValidation.isValidBDPhone(userDto.getPhonNumber()))
        {
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

                return ResponseEntity.ok(MUserMapper.mapToDto(authenticationService.sinUp(user)));
            }
            else if(userDto.getEmail() == null) return ResponseEntity.ok(MUserMapper.mapToDto(authenticationService.sinUp(user)));
            else return ResponseEntity.badRequest().body(null);
        }
        else return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/login")
    private ResponseEntity<JwtAuthenticationResponseDto> login(@RequestBody SignInRequestDto signInRequestDto){
        if(ImportantValidation.isValidBDPhone(signInRequestDto.getPhone()))
        {
            String phonNumber = signInRequestDto.getPhone();
            if(phonNumber.length() > 11) phonNumber = phonNumber.substring(3);
            signInRequestDto.setPhone(phonNumber);
            return ResponseEntity.ok(authenticationService.signIn(signInRequestDto));
        }
        else return ResponseEntity.badRequest().body(null);
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
            return ResponseEntity.badRequest().body("Appointment booked unsuccessfully");
        }
    }
    //for testing
    @GetMapping("/get_user_role={id}")
    private ResponseEntity<Set<String>> getUserRole(@PathVariable("id") Long id){
        MUser user = userService.getUserById(id);
        Set<String> roles = new HashSet<>();
        for (UserRole userRole : user.getUserRoles()) {
            roles.add(userRole.getRole());
        }
        return ResponseEntity.ok(roles);
    }
}
