package com.example.Appointment.Booking.System.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "doctor_appointment_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAppointmentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private LocalDate bookingDate;
    private LocalDate appointmentDate;
    private String status;
    private String doctorName;
    private String doctorSpecialization;
}
