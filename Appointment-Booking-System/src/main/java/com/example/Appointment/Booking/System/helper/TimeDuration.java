package com.example.Appointment.Booking.System.helper;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeDuration {
    public static String getTimeAgo(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);

        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return seconds + " seconds ago";
        } else if (seconds < 3600) {
            long minutes = seconds / 60;
            return minutes + " minutes ago";
        } else if (seconds < 86400) {
            long hours = seconds / 3600;
            return hours + " hours ago";
        } else if (seconds < 2592000) { // 30 days
            long days = seconds / 86400;
            return days + " days ago";
        } else if (seconds < 31104000) { // 12 months
            long months = seconds / 2592000;
            return months + " months ago";
        } else {
            long years = seconds / 31104000;
            return years + " years ago";
        }
    }

}
