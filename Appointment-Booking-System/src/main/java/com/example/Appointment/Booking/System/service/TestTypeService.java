package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.TestType;
import java.util.List;


public interface TestTypeService {

    TestType uploadTestType(TestType testType);
    List<String> getAllTestTypesName();
    TestType getTestTypeByName(String testTypeName);
    String deleteTestType(Long id);
    TestType updateTestType(TestType testType);
    List<TestType> getAllTestTypes();
}
