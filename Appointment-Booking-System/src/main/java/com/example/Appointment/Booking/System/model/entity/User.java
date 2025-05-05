package com.example.Appointment.Booking.System.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String phonNumber;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String email;
    private String gender;
    private LocalDate dateOfBirth;
}
