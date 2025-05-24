package com.example.Appointment.Booking.System.service.implementation;

import com.example.Appointment.Booking.System.model.entity.Lab;
import com.example.Appointment.Booking.System.repository.LabRepository;
import com.example.Appointment.Booking.System.service.LabService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabServiceImplementation implements LabService {
    private final LabRepository labRepository;

    @Override
    public Lab uploadLabDetails(Lab lab){
        try {
            return labRepository.save(lab);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public Lab getLabDetails(String labName){
        return labRepository.findByLabName(labName);
    }
    @Override
    public List<Lab> getLabDetailsLike(String labName){
        return labRepository.findByLabNameLike(labName);
    }
    @Override
    public Lab getLabDetailsById(Long id){
        return labRepository.findById(id).get();
    }
    @Override
    public List<Lab> getAllLabs(){
        return labRepository.findAll();
    }
    @Override
    public String deleteLab(Long id){
        if(labRepository.existsById(id))
        {
            labRepository.deleteById(id);
            return "Lab Deleted Successfully";
        }
        else return "Lab Not Found";
    }
    @Override
    public Lab updateLabDetails(Lab lab){
        try {
            return labRepository.save(lab);
        }catch (Exception e){
            return null;
        }
    }
}
