package com.example.Appointment.Booking.System.service.implementation;

import com.example.Appointment.Booking.System.model.entity.TestType;
import com.example.Appointment.Booking.System.repository.TestTypeRepository;
import com.example.Appointment.Booking.System.service.TestTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestTypeServiceImplementation implements TestTypeService {
    private final TestTypeRepository testTypeRepository;

    @Override
    public TestType uploadTestType(TestType testType){
        try {
            return testTypeRepository.save(testType);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public List<String> getAllTestTypesName(){
        List<String> allTypeName = new ArrayList<>();
        List<TestType> allTestTypes = testTypeRepository.findAll();
        for(TestType testType : allTestTypes){
            allTypeName.add(testType.getName());
        }
        return allTypeName;
    }
    @Override
    public TestType getTestTypeByName(String testTypeName){
        try {
            return testTypeRepository.findByName(testTypeName);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public String deleteTestType(Long id){
        if(testTypeRepository.existsById(id))
        {
            testTypeRepository.deleteById(id);
            return "Test Type Deleted Successfully";
        }
        else return "Test Type Not Found";
    }
    @Override
    public TestType updateTestType(TestType testType){
        try {
            return testTypeRepository.save(testType);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public List<TestType> getAllTestTypes(){
        return testTypeRepository.findAll();
    }
}
