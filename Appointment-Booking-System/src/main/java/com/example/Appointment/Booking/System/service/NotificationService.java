package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.Notification;

import java.util.List;

public interface NotificationService {
    Notification saveNotification(Notification notification);
    List<Notification> getOneUserNotification(Long userId);
    List<Notification> getOneUserContainNotification(Long userId,String notificationName);
}
