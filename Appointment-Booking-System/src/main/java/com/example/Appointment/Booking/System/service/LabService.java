package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.Lab;
import com.example.Appointment.Booking.System.repository.LabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabService {
    private final LabRepository labRepository;

    public Lab uploadLabDetails(Lab lab){
        try {
            return labRepository.save(lab);
        }catch (Exception e){
            return null;
        }
    }
    public Lab getLabDetails(String labName){
        return labRepository.findByLabName(labName);
    }
    public Lab getLabDetailsById(Long id){
        return labRepository.findById(id).get();
    }
    public List<Lab> getAllLabs(){
        return labRepository.findAll();
    }
    public String deleteLab(Long id){
        if(labRepository.existsById(id))
        {
            labRepository.deleteById(id);
            return "Lab Deleted Successfully";
        }
        else return "Lab Not Found";
    }
    public Lab updateLabDetails(Lab lab){
        try {
            return labRepository.save(lab);
        }catch (Exception e){
            return null;
        }
    }

}
