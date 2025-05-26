package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.helper.TimeDuration;
import com.example.Appointment.Booking.System.model.dto.NotificationDto;
import com.example.Appointment.Booking.System.model.entity.Notification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class NotificationMapper {
    public Notification MapToEntity(NotificationDto dto,Long senderId,String imageUrl,String senderName)
    {
        Notification notification = new Notification();
        notification.setUserId(dto.getUserId());
        notification.setSenderId(senderId);
        notification.setType(dto.getType());
        notification.setMessageBody(dto.getMessageBody());
        notification.setDate(LocalDateTime.now());
        notification.setImageUrl(imageUrl);
        notification.setSenderName(senderName);
        return notification;
    }
    public NotificationDto MapToDto(Notification notification){
        NotificationDto dto = new NotificationDto();
        dto.setId(notification.getId());
        dto.setUserId(notification.getUserId());
        dto.setType(notification.getType());
        dto.setMessageBody(notification.getMessageBody());
        dto.setDate(notification.getDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a")));//"dd MMMM yyyy, hh:mm a"
        dto.setTimeAgo(TimeDuration.getTimeAgo(notification.getDate()));
        dto.setImageUrl(notification.getImageUrl());
        dto.setSenderName(notification.getSenderName());
        return dto;
    }
}
