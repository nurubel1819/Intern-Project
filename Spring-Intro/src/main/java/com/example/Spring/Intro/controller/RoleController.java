package com.example.Spring.Intro.controller;

import com.example.Spring.Intro.model.dto.RoleDto;
import com.example.Spring.Intro.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/upload")
    private ResponseEntity<String> upload_new_role(RoleDto roleDto)
    {
        return new ResponseEntity<>(roleService.AddRole(roleDto), HttpStatus.OK);
    }

    @DeleteMapping ( "/delete")
    private ResponseEntity<String> delete_role(RoleDto roleDto)
    {
        return ResponseEntity.ok(roleService.DeleteRole(roleDto));
    }

    @PutMapping("/update")
    private ResponseEntity<String> update_role(RoleDto roleDto)
    {
        return ResponseEntity.ok(roleService.UpdateRole(roleDto));
    }

    @GetMapping("/get")
    private ResponseEntity<String> get_role(RoleDto roleDto)
    {
        return ResponseEntity.ok(roleService.GetRole(roleDto));
    }

    @PostMapping("/set_user_role")
    private ResponseEntity<String> set_user_role(RoleDto roleDto)
    {
        return ResponseEntity.ok(roleService.SetRole(roleDto));
    }
}
