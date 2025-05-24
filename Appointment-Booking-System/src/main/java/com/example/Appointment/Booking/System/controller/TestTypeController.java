package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.TestTypeDto;
import com.example.Appointment.Booking.System.model.entity.TestType;
import com.example.Appointment.Booking.System.model.mapper.TestTypeMapper;
import com.example.Appointment.Booking.System.service.TestTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test-types")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TestTypeController {
    private final TestTypeService testTypeService;
    private final TestTypeMapper testTypeMapper;

    @PostMapping("/registration")
    private ResponseEntity<TestTypeDto> uploadTestType(TestTypeDto testTypeDto){
        return ResponseEntity.ok(testTypeMapper.mapToDto(testTypeService.uploadTestType(testTypeMapper.mapToEntity(testTypeDto))));
    }

    @PostMapping("/get-by-name={name}")
    private ResponseEntity<TestTypeDto> getTestTypeByName(@PathVariable("name") String testTypeName){
        return ResponseEntity.ok(testTypeMapper.mapToDto(testTypeService.getTestTypeByName(testTypeName)));
    }

    @DeleteMapping("/delete-test-name={name}")
    private ResponseEntity<String> deleteTestTypeName(@PathVariable("name") String name)
    {
        return ResponseEntity.ok(testTypeService.deleteTestType(testTypeService.getTestTypeByName(name).getId()));
    }

    @PatchMapping("/update")
    private ResponseEntity<TestTypeDto> updateTestType(TestTypeDto testTypeDto){
        TestType testType = testTypeService.getTestTypeByName(testTypeDto.getName());
        testType.setDescription(testTypeDto.getDescription());
        return ResponseEntity.ok(testTypeMapper.mapToDto(testTypeService.updateTestType(testType)));
    }

    @GetMapping("/get-all-test-types")
    private ResponseEntity<List<TestTypeDto>> getAllTestTypes(){
        return ResponseEntity.ok(
                testTypeService.getAllTestTypes().stream()
                        .map(testTypeMapper::mapToDto)
                        .toList()
        );
    }
}
