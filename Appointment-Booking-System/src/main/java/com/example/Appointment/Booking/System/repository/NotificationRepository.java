package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByUserId(Long userId);
    List<Notification> findByUserIdAndMessageBodyContainingIgnoreCase(Long userId,String messageBody);

}
