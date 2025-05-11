package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.LabTestDto;
import com.example.Appointment.Booking.System.model.entity.LabTest;
import com.example.Appointment.Booking.System.model.entity.TestType;
import com.example.Appointment.Booking.System.model.mapper.LabTestMapper;
import com.example.Appointment.Booking.System.service.LabService;
import com.example.Appointment.Booking.System.service.LabTestService;
import com.example.Appointment.Booking.System.service.TestTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lab_test")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class LabTestController {
    private final LabTestService labTestService;
    private final LabTestMapper labTestMapper;
    private final LabService labService;
    private final TestTypeService testTypeService;

    @PostMapping("/upload_new_test")
    private ResponseEntity<String> uploadNewTest(@RequestBody LabTestDto labTestDto){
        LabTest labTest = labTestMapper.mapToEntity(labTestDto);
        System.out.println("labTest = "+labTest);// for testing
        LabTest saveLaveTest = labTestService.uploadLabTest(labTest);
        System.out.println("saveLaveTest = "+saveLaveTest);// for testing
        return ResponseEntity.ok("Upload successful \n"+
                "Test name = "+saveLaveTest.getTestName()+
                "Test type = "+saveLaveTest.getTestType());
    }

    @PostMapping("/update_test")
    private ResponseEntity<String> updateTest(@RequestBody LabTestDto labTestDto){
        LabTest labTest = labTestMapper.mapToEntity(labTestDto);
        LabTest updateLabTest = labTestService.updateLabTest(labTest);
        return ResponseEntity.ok("Update successful \n"+
                "Test name = "+updateLabTest.getTestName()+
                "Test type = "+updateLabTest.getTestType());
    }

    @DeleteMapping("/delete-test-name={name}")
    private ResponseEntity<String> deleteTest(@PathVariable("name") String name){
        return ResponseEntity.ok(labTestService.deleteLabTest(labTestService.getLabTestByName(name).getId()));
    }
}
