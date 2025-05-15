package com.example.Appointment.Booking.System.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hospital")
@Data
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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "lab_test_map",
            joinColumns = @JoinColumn(name = "lab_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private Set<LabTest> labTests = new HashSet<>();

}
