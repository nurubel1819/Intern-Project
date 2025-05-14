package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MUser, Long> {
    Optional<MUser> findByPhonNumber(String phonNumber);
    Optional<MUser> findByEmail(String email);
}
