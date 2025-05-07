package com.example.Appointment.Booking.System.service;


import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.model.entity.UserRole;
import com.example.Appointment.Booking.System.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MUser> user = userRepo.findByPhonNumber(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found...");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (UserRole userRole : user.get().getUserRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getName(),
                user.get().getPassword(),
                authorities
        );
    }
}