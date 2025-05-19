package com.example.Appointment.Booking.System.dummyData;

import com.example.Appointment.Booking.System.model.dto.MUserDto;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.mapper.MUserMapper;
import com.example.Appointment.Booking.System.repository.UserRepository;
import com.example.Appointment.Booking.System.service.AuthenticationService;
import com.example.Appointment.Booking.System.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final MUserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final RoleService roleService;


    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0) {
            /*roleService.addNewRole("USER");
            roleService.addNewRole("ADMIN");
            //create first user as a ADMIN and USER role
            MUserDto userDto = new MUserDto();
            userDto.setName("MD Nasir Udidn");
            userDto.setPhonNumber("01753278407");
            userDto.setPassword("123");
            userDto.setEmail("nurubel70@gmail.com");
            userDto.setGender("Male");
            userDto.setDateOfBirth(LocalDate.now());

            MUser user = userMapper.mapToEntity(userDto);
            user.setUserRoles(Set.of(roleService.findRoleByName("USER"),roleService.findRoleByName("ADMIN")));
            authenticationService.sinUp(user);
            //authenticationService.sinUp(userMapper.mapToEntityWithRoles(userDto));


            //create second user as a USER Role
            MUserDto dto2 = new MUserDto();
            dto2.setName("Sadia akter");
            dto2.setPhonNumber("01753278408");
            dto2.setPassword("123");
            dto2.setEmail("sadia@gmail.com");
            dto2.setGender("female");
            dto2.setDateOfBirth(LocalDate.now());

            MUser user2 = userMapper.mapToEntity(dto2);
            user2.setUserRoles(Set.of(roleService.findRoleByName("USER")));
            authenticationService.sinUp(user2);
            //authenticationService.sinUp(userMapper.mapToEntityWithRoles(dto2));*/

        }
    }
}
