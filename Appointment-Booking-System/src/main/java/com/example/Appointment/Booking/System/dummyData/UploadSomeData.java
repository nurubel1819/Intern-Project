package com.example.Appointment.Booking.System.dummyData;

import com.example.Appointment.Booking.System.model.dto.DoctorAvailabilityDto;
import com.example.Appointment.Booking.System.model.dto.DoctorDto;
import com.example.Appointment.Booking.System.model.dto.LabTestAppointmentDto;
import com.example.Appointment.Booking.System.model.dto.MUserDto;
import com.example.Appointment.Booking.System.model.entity.*;
import com.example.Appointment.Booking.System.model.mapper.DoctorMapper;
import com.example.Appointment.Booking.System.model.mapper.MUserMapper;
import com.example.Appointment.Booking.System.repository.LabTestRepository;
import com.example.Appointment.Booking.System.repository.UserRepository;
import com.example.Appointment.Booking.System.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UploadSomeData {

    private final UserRepository userRepository;
    private final LabTestRepository labTestRepository;
    private final AuthenticationService authenticationService;
    private final MUserMapper userMapper;
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final TestTypeService testTypeService;
    private final LabService labService;
    private final NotificationService notificationService;

    public void uploadSomeUser(){
        if(userRepository.count() == 0) {

            //create first user as a ADMIN and USER role
            MUserDto userDto = new MUserDto();
            userDto.setName("MD Nasir Udidn");
            userDto.setPhonNumber("01753278407");
            userDto.setPassword("123");
            userDto.setEmail("nurubel70@gmail.com");
            userDto.setGender("Male");
            userDto.setDateOfBirth(LocalDate.now());

            MUser user = authenticationService.sinUp(userMapper.mapToEntity(userDto));

            //add one notification to user
            Notification notification = new Notification();
            notification.setSenderId(user.getId());
            notification.setUserId(user.getId());
            notification.setType("Appointment");
            notification.setImageUrl("/image/doctor_image.jpg");
            notification.setSenderName(user.getName());
            notification.setDate(LocalDateTime.now());
            notification.setMessageBody("This message set from dummy data");
            notificationService.saveNotification(notification);


            //register first user as a doctor
            DoctorDto doctorDto = new DoctorDto();
            doctorDto.setName(user.getName());
            doctorDto.setPhone(user.getPhonNumber());
            doctorDto.setEmail(user.getEmail());
            doctorDto.setGender(user.getGender());
            doctorDto.setDateOfBirth(user.getDateOfBirth());
            doctorDto.setAddress("Dhaka");
            doctorDto.setSpecialization("Neurologist");
            doctorDto.setExperience("10 years");
            doctorDto.setQualification("MBBS,FCPS");
            Doctor doctor = doctorMapper.mapToEntity(doctorDto);
            doctor = doctorService.uploadDoctor(doctor);

            //add time slot for this doctor
            DoctorAvailabilityDto dto = new DoctorAvailabilityDto();
            dto.setDoctorId(doctor.getId());
            dto.setDate(LocalDate.now());// add today available
            doctorService.setDoctorAvailability(dto);


            //create second user as a USER Role
            MUserDto dto2 = new MUserDto();
            dto2.setName("Sadia akter");
            dto2.setPhonNumber("01753278408");
            dto2.setPassword("123");
            dto2.setEmail("sadia@gmail.com");
            dto2.setGender("female");
            dto2.setDateOfBirth(LocalDate.now());
            user = authenticationService.sinUp(userMapper.mapToEntity(dto2));

            //register second user as a doctor
            doctorDto.setName(user.getName());
            doctorDto.setPhone(user.getPhonNumber());
            doctorDto.setEmail(user.getEmail());
            doctorDto.setGender(user.getGender());
            doctorDto.setDateOfBirth(user.getDateOfBirth());
            doctorDto.setAddress("Dhaka");
            doctorDto.setSpecialization("Neurologist");
            doctorDto.setExperience("10 years");
            doctorDto.setQualification("MBBS,FCPS");
            doctor = doctorMapper.mapToEntity(doctorDto);
            doctor = doctorService.uploadDoctor(doctor);
            //here set how much appointment can be set by doctor

            //3rd User
            MUserDto dto3 = new MUserDto();
            dto3.setName("Aniul Islam");
            dto3.setPhonNumber("01753278409");
            dto3.setPassword("123");
            dto3.setEmail("anis@gmail.com");
            dto3.setGender("Male");
            dto3.setDateOfBirth(LocalDate.now());
            authenticationService.sinUp(userMapper.mapToEntity(dto3));

            //4Th User
            MUserDto dto4 = new MUserDto();
            dto4.setName("Nasima Beguam");
            dto4.setPhonNumber("01749402012");
            dto4.setPassword("123");
            dto4.setEmail("nasima@gmail.com");
            dto4.setGender("Female");
            dto4.setDateOfBirth(LocalDate.now());
            authenticationService.sinUp(userMapper.mapToEntity(dto4));
        }
    }
    public void uploadSomeLabTest(){
        if(labTestRepository.count() == 0) {

            //---------------------------------------Test Type Upload---------------------------------------
            TestType testType = new TestType();
            testType.setName("Blood Test");
            testType.setDescription("Blood test for blood pressure, heart rate, respiratory rate, etc.");
            TestType blood = testTypeService.uploadTestType(testType);

            testType = new TestType();
            testType.setName("X-Ray");
            testType.setDescription("X-ray imaging of the body");
            TestType xray = testTypeService.uploadTestType(testType);

            testType = new TestType();
            testType.setName("Ultrasound");
            testType.setDescription("Ultrasound imaging of the body");
            TestType ultrasound = testTypeService.uploadTestType(testType);

            testType = new TestType();
            testType.setName("CT Scan");
            testType.setDescription("CT Scan imaging of the body");
            TestType ctscan = testTypeService.uploadTestType(testType);

            //---------------------------------------Lab or Hospital Upload---------------------------------------
            Lab lab = new Lab();
            lab.setLabName("Square ");
            lab.setAddress("Dhaka");
            Lab square = labService.uploadLabDetails(lab);

            lab = new Lab();
            lab.setLabName("LABAID ");
            lab.setAddress("Dhaka");
            Lab labaid = labService.uploadLabDetails(lab);

            lab = new Lab();
            lab.setLabName("DMC");
            lab.setAddress("Dhaka");
            Lab dmc = labService.uploadLabDetails(lab);

            //---------------------------------------Lab Test Upload---------------------------------------
            LabTest labTest = new LabTest();
            labTest.setTestName("CBC");
            labTest.setPrice("200");
            labTest.setDescription("Blood test for blood pressure, heart rate, respiratory rate, etc.");
            labTest.setDurationInHours(Duration.ofHours(50));
            labTest.setTestType(blood);
            labTest.getLabs().add(square);
            square.getLabTests().add(labTest);
            labService.uploadLabDetails(lab); //lab is this owner side so upload labTest in lab side

            labTest = new LabTest();
            labTest.setTestName("Chest X-Ray");
            labTest.setPrice("100");
            labTest.setDescription("X-ray imaging of the body");
            labTest.setDurationInHours(Duration.ofHours(2));
            labTest.setTestType(xray);
            labTest.getLabs().add(dmc);
            dmc.getLabTests().add(labTest);
            labService.uploadLabDetails(lab);//lab is this owner side so upload labTest in lab side

        }
    }
}
