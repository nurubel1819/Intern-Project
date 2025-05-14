package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.jwt.JwtUtils;
import com.example.Appointment.Booking.System.model.dto.JwtAuthenticationResponseDto;
import com.example.Appointment.Booking.System.model.dto.SignInRequestDto;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public MUser sinUp(MUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        MUser saveUser = userService.saveNewUser(user);
        if(saveUser.getId()==1L) // here make first user is admin
        {
            UserRole role = roleService.findRoleByName("ADMIN");
            if(role!=null)
            {
                roleService.setUserRole(saveUser.getId(),role.getId());
            }
            else
            {
                roleService.addNewRole("ADMIN");
                role = roleService.findRoleByName("ADMIN");
                if(role!=null)
                {
                    roleService.setUserRole(saveUser.getId(),role.getId());
                }
            }
        }
        return saveUser;
        //return userService.saveNewUser(user);
    }

    public JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequestDto.getPhone(),signInRequestDto.getPassword()
                    ));

            var user = userRepository.findByPhonNumber(signInRequestDto.getPhone()).orElseThrow(()-> new IllegalArgumentException("Invalid username or password"));
            var token = jwtUtils.generateToken(user.getPhonNumber());
            //var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

            System.out.println("Token: "+token);
            JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();
            jwtAuthenticationResponseDto.setToken(token);
            return jwtAuthenticationResponseDto;
        }catch (Exception e){
            JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();
            jwtAuthenticationResponseDto.setToken(null);
            return jwtAuthenticationResponseDto;
        }
    }

}
