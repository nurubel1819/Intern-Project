package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.TestType;
import com.example.Appointment.Booking.System.repository.TestTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestTypeService {

    private final TestTypeRepository testTypeRepository;

    public TestType uploadTestType(TestType testType){
        try {
            return testTypeRepository.save(testType);
        }catch (Exception e){
            return null;
        }
    }

    public TestType getTestTypeByName(String testTypeName){
        return testTypeRepository.findByName(testTypeName);
    }

    public List<TestType> getTestTypeByNameLike(String testTypeName){
        return testTypeRepository.findByNameLike(testTypeName);
    }

    public String deleteTestType(Long id){
        if(testTypeRepository.existsById(id))
        {
            testTypeRepository.deleteById(id);
            return "Test Type Deleted Successfully";
        }
        else return "Test Type Not Found";
    }

    public TestType updateTestType(TestType testType){
        try {
            return testTypeRepository.save(testType);
        }catch (Exception e){
            return null;
        }
    }

    public List<TestType> getAllTestTypes(){
        return testTypeRepository.findAll();
    }

}
