package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.LabTestDto;
import com.example.Appointment.Booking.System.model.entity.Lab;
import com.example.Appointment.Booking.System.model.entity.LabTest;
import com.example.Appointment.Booking.System.model.mapper.LabTestMapper;
import com.example.Appointment.Booking.System.service.LabService;
import com.example.Appointment.Booking.System.service.LabTestService;
import com.example.Appointment.Booking.System.service.TestTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    private ResponseEntity<?> uploadNewTest(@RequestBody LabTestDto labTestDto){
        // i must be save from owner class
        // here owner class is lab
        try {
            Lab lab = labService.getLabDetails(labTestDto.getLabName());
            if(testTypeService.getTestTypeByName(labTestDto.getTestType()) == null){
                return ResponseEntity.badRequest().body(Map.of("message", "test type not found"));
            }
            if (lab == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "lab not found"));
            }

            LabTest labTest = labTestMapper.mapToEntity(labTestDto);
            //labTest.setLabs(Set.of(lab));
            labTest.getLabs().add(lab);

            lab.getLabTests().add(labTest);

            labService.uploadLabDetails(lab);
            return ResponseEntity.ok("Upload successful \n"+
                    "Test name = "+labTest.getTestName()+
                    "Test type = "+labTest.getTestType());

        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("message","Upload error from lab test controller"));
        }
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
