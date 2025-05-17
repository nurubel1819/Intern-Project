package com.example.Appointment.Booking.System.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lab")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String labName;
    @Column(nullable = false)
    private String address;
    private double rating;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "lab_map_lab_test",
            joinColumns = @JoinColumn(name = "lab_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private Set<LabTest> labTests = new HashSet<>();

}
