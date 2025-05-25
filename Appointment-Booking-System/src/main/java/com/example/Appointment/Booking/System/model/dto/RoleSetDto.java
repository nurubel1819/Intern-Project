package com.example.Appointment.Booking.System.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleSetDto {
    @Schema(hidden = true)
    private Long userId;
    private String userPhone;
    private String roleName;
}
