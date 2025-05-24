package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.LabTest;
import java.util.List;

public interface LabTestService {
    LabTest uploadLabTest(LabTest labTest);
    List<LabTest> getAllLabTest();
    List<LabTest> getLabTestContain(String labTestName);
    LabTest getLabTestByName(String labTestName);
    public List<LabTest> getLabTestByNameLike(String labTestName);
    String deleteLabTest(Long id);
    LabTest updateLabTest(LabTest labTest);
    LabTest getLabTestById(Long id);
    LabTest getLabTestByTestName(String testName);
    List<LabTest> getLabTestByTestNameLike(String testName);
}
