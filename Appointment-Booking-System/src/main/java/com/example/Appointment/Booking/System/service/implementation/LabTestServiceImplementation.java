package com.example.Appointment.Booking.System.service.implementation;

import com.example.Appointment.Booking.System.model.entity.LabTest;
import com.example.Appointment.Booking.System.repository.LabTestRepository;
import com.example.Appointment.Booking.System.service.LabTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabTestServiceImplementation implements LabTestService {
    private final LabTestRepository labTestRepository;

    @Override
    public LabTest uploadLabTest(LabTest labTest){
        try {
            return labTestRepository.save(labTest);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public List<LabTest> getAllLabTest(){
        return labTestRepository.findAll();
    }
    @Override
    public List<LabTest> getLabTestContain(String labTestName){
        try {
            return labTestRepository.findByTestNameContaining(labTestName);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public LabTest getLabTestByName(String labTestName){
        return labTestRepository.findByTestName(labTestName);
    }
    @Override
    public List<LabTest> getLabTestByNameLike(String labTestName){
        return labTestRepository.findByTestNameLike(labTestName);
    }
    @Override
    public String deleteLabTest(Long id){
        if(labTestRepository.existsById(id))
        {
            labTestRepository.deleteById(id);
            return "Lab Test Deleted Successfully";
        }
        else return "Lab Test Not Found";
    }
    @Override
    public LabTest updateLabTest(LabTest labTest){
        try {
            return labTestRepository.save(labTest);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public LabTest getLabTestById(Long id){
        return labTestRepository.findById(id).get();
    }
    @Override
    public LabTest getLabTestByTestName(String testName){
        return labTestRepository.findByTestName(testName);
    }
    @Override
    public List<LabTest> getLabTestByTestNameLike(String testName){
        return labTestRepository.findByTestNameLike(testName);
    }
}
