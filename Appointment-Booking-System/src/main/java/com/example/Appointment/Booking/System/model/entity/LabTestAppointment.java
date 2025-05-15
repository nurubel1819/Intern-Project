package com.example.Appointment.Booking.System.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "lab_test_appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabTestAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate bookingDate;
    @Column(nullable = false)
    private LocalDate appointmentDate;
    private String note;
    private String labName;

    @ManyToOne
    @JoinColumn(name = "lab_test_id")
    private LabTest labTest;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MUser user;
}
