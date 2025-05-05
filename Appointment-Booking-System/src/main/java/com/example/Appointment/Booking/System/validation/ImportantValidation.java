package com.example.Appointment.Booking.System.validation;

import java.util.regex.Pattern;

public class ImportantValidation {
    // regex for email
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    // regex for BD phone number
    private static final String BD_PHONE_REGEX = "^(\\+8801|8801|01)[3-9][0-9]{8}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern BD_PHONE_PATTERN = Pattern.compile(BD_PHONE_REGEX);

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }
    public static boolean isValidBDPhone(String phone) {
        if (phone == null) return false;
        return BD_PHONE_PATTERN.matcher(phone).matches();
    }
}

