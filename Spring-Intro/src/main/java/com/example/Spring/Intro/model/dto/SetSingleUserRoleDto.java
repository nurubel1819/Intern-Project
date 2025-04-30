package com.example.Spring.Intro.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetSingleUserRoleDto {
    private Long userId;
    private String roleName;
}
