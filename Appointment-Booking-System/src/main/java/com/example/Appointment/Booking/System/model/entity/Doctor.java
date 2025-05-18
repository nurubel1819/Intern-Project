package com.example.Appointment.Booking.System.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String gender;
    private String address;
    @Column(nullable = false)
    private String specialization;// cardiologist,
    @Column(nullable = false)
    private String experience;
    @Column(nullable = false)
    private String qualification;//description
    private LocalDate dateOfBirth;
    private String image;//optional

    @OneToMany
    private Set<DoctorAppointment> appointments = new HashSet<>();


}
