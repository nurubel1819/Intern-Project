package com.example.Spring.Intro.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String roleName;
    private String description;
    private List<Long> userIds;
}
