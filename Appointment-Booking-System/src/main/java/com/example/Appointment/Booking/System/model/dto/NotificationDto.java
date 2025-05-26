package com.example.Appointment.Booking.System.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private String type;
    private Long id;
    private Long userId;
    private String imageUrl;
    private String senderName;
    private String date;
    private String timeAgo;
    private String messageBody;
}

