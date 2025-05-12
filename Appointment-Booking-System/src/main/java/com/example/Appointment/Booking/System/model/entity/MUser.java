package com.example.Appointment.Booking.System.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class MUser {
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

    @ManyToMany(mappedBy = "users",fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<UserRole> userRoles=new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Doctor> doctors = new HashSet<>();

    @OneToMany
    private Set<DoctorAppointment> doctorAppointments = new HashSet<>();
}
