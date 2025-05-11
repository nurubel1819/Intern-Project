package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.TestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestTypeRepository extends JpaRepository<TestType, Long> {
    TestType findByName(String categoryName);
    List<TestType> findByNameLike(String categoryName);
}
