package com.example.Appointment.Booking.System.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "lab_test")
@NoArgsConstructor
@AllArgsConstructor
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String testName;
    @Column(nullable = false)
    private String price;
    private String description;
    private Duration durationInHours;

    @ManyToMany(mappedBy = "labTests")
    private Set<Lab> labs = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "test_type_id")
    private TestType testType;

    @OneToMany(mappedBy = "labTest")
    private Set<LabTestAppointment> labTestAppointments = new HashSet<>();

}
