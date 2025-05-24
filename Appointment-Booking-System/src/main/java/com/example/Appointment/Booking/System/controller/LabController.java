package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.LabDto;
import com.example.Appointment.Booking.System.model.entity.Lab;
import com.example.Appointment.Booking.System.model.mapper.LabMapper;
import com.example.Appointment.Booking.System.service.LabService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/labs")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class LabController {

    private final LabService labService;
    private final LabMapper labMapper;

    @PostMapping("/registration")
    private ResponseEntity<?> uploadLabDetails(LabDto labDto){
        try {
            Lab lab = labMapper.mapToEntity(labDto);
            Lab saveLab = labService.uploadLabDetails(lab);
            if(saveLab == null){
                return ResponseEntity.badRequest().body(Map.of("message", "Lab name already exist"));
            }
            else return ResponseEntity.ok("Upload successful \n"+
                    "Lab name = "+lab.getLabName());
        } catch (Exception e) {
            System.out.println("Exception = "+e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("message","Upload error\n" +
                    "Exception is = " +e.getMessage()+"\n"+"input is wrong\n" +
                    "Exception become from lab controller"));
        }
    }

    @PatchMapping("/update")
    private ResponseEntity<?> updateLabDetails(LabDto labDto){
        if(labService.getLabDetails(labDto.getLabName()) == null){
            return ResponseEntity.badRequest().body(Map.of("message", "lab name not found"));
        }
        Lab lab = labService.getLabDetails(labDto.getLabName());
        lab.setAddress(labDto.getAddress());
        lab = labService.updateLabDetails(lab);
        return ResponseEntity.ok(labMapper.mapToDto(lab));
    }

    @DeleteMapping(value = "/delete-id={id}")
    private ResponseEntity<String> deleteLab(@PathVariable("id") Long id){
        return ResponseEntity.ok(labService.deleteLab(id));
    }

    @GetMapping("/get-lab-by-name={labName}")
    private ResponseEntity<?> getLabDetails(@PathVariable("labName") String labName){
        try {
            Lab lab = labService.getLabDetails(labName);
            return ResponseEntity.ok(labMapper.mapToDto(lab));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("message", "lab name not found"));
        }
    }

    @GetMapping("/get-lab-by-id={id}")
    private ResponseEntity<?> getLabDetailsById(@PathVariable("id") Long id){
        try {
            Lab lab = labService.getLabDetailsById(id);
            return ResponseEntity.ok(labMapper.mapToDto(lab));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("message", "lab name not found"));
        }
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
