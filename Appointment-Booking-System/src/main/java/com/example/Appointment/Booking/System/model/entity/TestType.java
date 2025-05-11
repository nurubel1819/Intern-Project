package com.example.Appointment.Booking.System.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Test;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "test_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    /*@OneToMany(mappedBy = "testType")
    private Set<LabTest> tests = new HashSet<>();*/
}
