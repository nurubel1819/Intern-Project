package com.example.Appointment.Booking.System.service.implementation;

import com.example.Appointment.Booking.System.model.entity.Notification;
import com.example.Appointment.Booking.System.repository.NotificationRepository;
import com.example.Appointment.Booking.System.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImplementation implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification saveNotification(Notification notification) {
        try {
            return notificationRepository.save(notification);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Notification> getOneUserNotification(Long userId) {
        try {
            return notificationRepository.findByUserId(userId);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Notification> getOneUserContainNotification(Long userId,String notificationName) {
        try {
            return notificationRepository.findByUserIdAndMessageBodyContainingIgnoreCase(userId,notificationName);
        }catch (Exception e){
            return null;
        }
    }
}
