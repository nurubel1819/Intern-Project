package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.LabDto;
import com.example.Appointment.Booking.System.model.mapper.LabMapper;
import com.example.Appointment.Booking.System.service.LabService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labs")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class LabController {

    private final LabService labService;
    private final LabMapper labMapper;

    @PostMapping("/registration")
    private ResponseEntity<LabDto> uploadLabDetails(LabDto labDto){
        return ResponseEntity.ok(labMapper.mapToDto(labService.uploadLabDetails(labMapper.mapToEntity(labDto))));
    }

    @PatchMapping("/update")
    private ResponseEntity<LabDto> updateLabDetails(LabDto labDto){
        return ResponseEntity.ok(labMapper.mapToDto(labService.updateLabDetails(labMapper.mapToEntity(labDto))));
    }

    @DeleteMapping(value = "/delete-id={id}")
    private ResponseEntity<String> deleteLab(@PathVariable("id") Long id){
        return ResponseEntity.ok(labService.deleteLab(id));
    }

    @GetMapping("/get-lab-by-name={labName}")
    private ResponseEntity<LabDto> getLabDetails(@PathVariable("labName") String labName){
        return ResponseEntity.ok(labMapper.mapToDto(labService.getLabDetails(labName)));
    }

    @GetMapping("/get-lab-by-id={id}")
    private ResponseEntity<LabDto> getLabDetailsById(@PathVariable("id") Long id){
        return ResponseEntity.ok(labMapper.mapToDto(labService.getLabDetailsById(id)));
    }

    @GetMapping("/get-all-lab-details")
    public ResponseEntity<List<LabDto>> findAll() {
        return ResponseEntity.ok(
                labService.getAllLabs().stream()
                        .map(labMapper::mapToDto)
                        .toList()
        );
    }

}
