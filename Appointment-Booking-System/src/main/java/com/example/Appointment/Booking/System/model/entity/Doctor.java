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
    private String password;
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

    @ManyToMany
    @JoinTable(
            name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set<MUser> users = new HashSet<>();

    @OneToMany(mappedBy = "doctor")
    private Set<DoctorAppointment> appointments = new HashSet<>();


}
