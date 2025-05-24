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
    @PatchMapping("/update-user-name/{id}")
    private ResponseEntity<?> updateUserDetails(@PathVariable("id") Long id, @RequestParam String userName){
        try {
            MUser user = userService.getUserById(id);
            user.setName(userName);
            user = userService.updateUser(user);
            return ResponseEntity.ok(MUserMapper.mapToDto(user));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("message","User not found"));
        }
    }

}
