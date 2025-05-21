package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.LabTest;
import com.example.Appointment.Booking.System.repository.LabTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabTestService {
    private final LabTestRepository labTestRepository;

    public LabTest uploadLabTest(LabTest labTest){
        try {
            return labTestRepository.save(labTest);
        }catch (Exception e){
            return null;
        }
    }
    public List<LabTest> getAllLabTest(){
        return labTestRepository.findAll();
    }
    public List<LabTest> getLabTestContain(String labTestName){
        try {
            return labTestRepository.findByTestNameContaining(labTestName);
        }catch (Exception e){
            return null;
        }
    }
    public LabTest getLabTestByName(String labTestName){
        return labTestRepository.findByTestName(labTestName);
    }
    public List<LabTest> getLabTestByNameLike(String labTestName){
        return labTestRepository.findByTestNameLike(labTestName);
    }
    public String deleteLabTest(Long id){
        if(labTestRepository.existsById(id))
        {
            labTestRepository.deleteById(id);
            return "Lab Test Deleted Successfully";
        }
        else return "Lab Test Not Found";
    }
    public LabTest updateLabTest(LabTest labTest){
        try {
            return labTestRepository.save(labTest);
        }catch (Exception e){
            return null;
        }
    }
    public LabTest getLabTestById(Long id){
        return labTestRepository.findById(id).get();
    }
    public LabTest getLabTestByTestName(String testName){
        return labTestRepository.findByTestName(testName);
    }
    public List<LabTest> getLabTestByTestNameLike(String testName){
        return labTestRepository.findByTestNameLike(testName);
    }
}
