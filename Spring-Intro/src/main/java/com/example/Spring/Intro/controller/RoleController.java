package com.example.Spring.Intro.controller;

import ch.qos.logback.core.model.Model;
import com.example.Spring.Intro.model.dto.RoleDto;
import com.example.Spring.Intro.model.dto.UserDto;
import com.example.Spring.Intro.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/upload")
    private ResponseEntity<String> uploadNewRole(RoleDto roleDto)
    {
        return new ResponseEntity<>(roleService.AddRole(roleDto), HttpStatus.OK);
    }

    @DeleteMapping ( "/delete/id={id}")
    private ResponseEntity<String> deleteRole(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok(roleService.DeleteRole(id));
    }

    @PatchMapping("/update")
    private ResponseEntity<String> updateRole(RoleDto roleDto)
    {
        return ResponseEntity.ok(roleService.UpdateRole(roleDto));
    }

    @GetMapping("/get/id={id}")
    private ResponseEntity<String> getRole(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok(roleService.GetRole(id));
    }

    /*@PostMapping("/set_user_role")
    private ResponseEntity<String> setUserRole(RoleDto roleDto)
    {
        return ResponseEntity.ok(roleService.SetRole(roleDto));
    }*/
}
