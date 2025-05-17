package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.dto.*;
import com.example.Appointment.Booking.System.model.entity.*;
import com.example.Appointment.Booking.System.model.mapper.DoctorMapper;
import com.example.Appointment.Booking.System.model.mapper.LabTestAppointmentMapper;
import com.example.Appointment.Booking.System.model.mapper.LabTestMapper;
import com.example.Appointment.Booking.System.model.mapper.MUserMapper;
import com.example.Appointment.Booking.System.repository.CountAppointmentRepository;
import com.example.Appointment.Booking.System.repository.RoleRepository;
import com.example.Appointment.Booking.System.service.*;
import com.example.Appointment.Booking.System.validation.ImportantValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class ThymeleafController {

    private final UserService userService;
    private final MUserMapper userMapper;
    private final DoctorService doctorService;
    private final LabTestService labTestService;
    private final DoctorAppointmentService doctorAppointmentService;
    private final AuthenticationService authenticationService;
    private final RoleRepository roleRepository;
    private final LabTestMapper labTestMapper;
    private final LabService labService;
    private final TestTypeService testTypeService;
    private final LabTestAppointmentService labTestAppointmentService;
    private final CountAppointmentRepository countAppointmentRepository;
    private final LabTestAppointmentMapper labTestAppointmentMapper;
    private final DoctorMapper doctorMapper;

    @GetMapping("/")
    public String homePage() {
        return "HomePage";
    }
    @GetMapping("/registration")      //-------------------------registration-----------------------
    public String showRegistrationForm(Model model) {
        model.addAttribute("MUser", new MUserDto());
        return "Registration";
    }
    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("MUser") MUserDto userDto,Model model) {
        System.out.println("user = "+userDto);
        model.addAttribute("MUser", userDto);
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())) return "Registration";
        else if(!ImportantValidation.isValidBDPhone(userDto.getPhonNumber())) return "Registration";
        else if(userDto.getEmail()!=null && !ImportantValidation.isValidEmail(userDto.getEmail())) return "Registration";
        else
        {
            try {
                MUser user = userMapper.mapToEntity(userDto);

                UserRole userRole = roleRepository.findByRole("USER");
                if(userRole==null)
                {
                    userRole = new UserRole();
                    userRole.setRole("USER");
                }
                Set<MUser> users = userRole.getUsers();
                users.add(user);
                userRole.setUsers(users);

                user.setUserRoles(Set.of(userRole));

                authenticationService.sinUp(user);
                return "redirect:/doctor-dashboard";

            }catch (Exception e){
                System.out.println("Exception = "+e.getMessage());
                return "Registration";
            }
        }
    }
    @GetMapping("/login")         //-------------------------Login-------------------
    public String loginPage(Model model){
        model.addAttribute("login_request", new SignInRequestDto());
        return "Login";
    }
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("login_request") SignInRequestDto signInRequestDto,Model model){


        System.out.println("User phone = "+signInRequestDto.getPhone() );
        System.out.println("User password = "+signInRequestDto.getPassword());

        // Authenticate
        JwtAuthenticationResponseDto status = authenticationService.signIn(signInRequestDto);
        if(status.getToken()!=null) return "redirect:/doctor-dashboard";
        model.addAttribute("login_request", signInRequestDto);
        model.addAttribute("errorMessage", "Invalid phone number or password");
        return "Login";
    }
    @GetMapping("/upload_new_test")    //-------------------------Upload New Test--------------------------
    public String uploadNewTest(Model model){
        model.addAttribute("labTestDto", new LabTestDto());

        // load lab details
        List<Lab> labList = labService.getAllLabs();
        model.addAttribute("labList", labList);

        List<String> testTypeList = testTypeService.getAllTestTypesName();
        model.addAttribute("testTypeList", testTypeList);
        return "LabTestUpload";
    }
    @PostMapping("/upload_new_test")
    public String saveLabTest(@ModelAttribute LabTestDto labTestDto) {

        try {
            Lab lab = labService.getLabDetails(labTestDto.getLabName());
            if(testTypeService.getTestTypeByName(labTestDto.getTestType()) == null){
                return "redirect:/upload_new_test?message=test type not found";
            }
            if (lab == null) {
                return "redirect:/upload_new_test?message=lab not found";
            }

            LabTest labTest = labTestMapper.mapToEntity(labTestDto);
            labTest.getLabs().add(lab);

            lab.getLabTests().add(labTest);
            System.out.println("labTest = "+labTest);
            labService.uploadLabDetails(lab);
            return "redirect:/?message=upload successful";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/upload_new_test?message=upload unsuccessful";
        }
    }
    @GetMapping("/lab-test-dashboard")     //-------------------------Lab Test Dashboard ------------------------
    public String labTestDashboard(Model model){
        model.addAttribute("allTest",labTestService.getAllLabTest());
        model.addAttribute("TestAppointmentHistory",labTestAppointmentService.getOneUserHistory(1L));
        System.out.println("TestAppointmentHistory = "+labTestAppointmentService.getOneUserHistory(1L));
        return "LabTestDashBoard";
    }
    @GetMapping("/doctor-dashboard")     //-------------------------Doctor Dashboard ------------------------
    public String doctorDashboard(Model model){
        List<Doctor> allDoctorList = doctorService.getAllDoctors();
        List<DoctorAvailableStatusDto> allDoctorWithStatus = new ArrayList<>();
        for(Doctor doctor:allDoctorList){
            DoctorAvailableStatusDto doctorAvailableStatusDto = new DoctorAvailableStatusDto();
            doctorAvailableStatusDto.setId(doctor.getId());
            doctorAvailableStatusDto.setName(doctor.getName());
            doctorAvailableStatusDto.setQualification(doctor.getQualification());
            doctorAvailableStatusDto.setSpecialization(doctor.getSpecialization());

            //add status
            CountAppointment status = countAppointmentRepository.findByDoctorId(doctor.getId());
            if(status!=null){
                doctorAvailableStatusDto.setStatus("Doctor on duty");
                doctorAvailableStatusDto.setTotalPossibilityPatient(status.getTotalPatient());
            }
            else{
                doctorAvailableStatusDto.setStatus("Not present today");
                doctorAvailableStatusDto.setTotalPossibilityPatient(0);
            }
            allDoctorWithStatus.add(doctorAvailableStatusDto);

        }
        model.addAttribute("doctors",allDoctorWithStatus);
        model.addAttribute("appointmentHistory",doctorAppointmentService.getHistory(1L));
        return "DoctorDashBoard";
    }
    @GetMapping("/doctor-appointment-book/{doctorId}")  //---------------Doctor Appointment confirm ----------------
    public String showDoctorAppointmentForm(@PathVariable Long doctorId, Model model) {
        Doctor doctor = doctorService.findDoctorById(doctorId);
        model.addAttribute("doctor", doctor);

        // create dto and set doctor id
        DoctorAppointmentConfirmDto dto = new DoctorAppointmentConfirmDto();
        dto.setDoctorId(doctorId);

        model.addAttribute("doctorAppointmentConfirmDto", dto);
        return "DoctorAppointmentForm";
    }

    @PostMapping("/doctor-appointment-book/confirm")
    public String confirmDoctorAppointment(@ModelAttribute DoctorAppointmentConfirmDto dto) {
        Long patientId = userService.getUserByPhone(dto.getUserPhone()).getId();
        System.out.println("patientId = "+patientId+" doctorId = "+dto.getDoctorId());
        try {
            String book_status =  userService.bookDoctor(dto.getDoctorId(), patientId);
            if(book_status.equals("doctor appointment booked")) return "redirect:/doctor-dashboard";
            return "redirect:/doctor-dashboard";
        }catch (Exception e){
            System.out.println("Exception confirm doctor appointment post mathod = "+e.getMessage());
            return "redirect:/doctor-dashboard";
        }
    }
    @GetMapping("/lab-test-appointment-book/{labTestId}")   //-------------------Lab test appointment confirm-------------
    public String showLabTestAppointmentForm(@PathVariable Long labTestId, Model model) {
        LabTest labTest = labTestService.getLabTestById(labTestId);
        model.addAttribute("labTest", labTest);

        LabTestAppointmentDto dto = new LabTestAppointmentDto();
        dto.setId(labTestId);
        dto.setTestName(labTest.getTestName());

        // load lab details
        Set<Lab> labList = labTest.getLabs();
        model.addAttribute("labList", labList);

        model.addAttribute("labTestAppointmentDto", dto);
        return "LabTestAppointmentForm";
    }
    @PostMapping("/lab-test-appointment-book/confirm")
    public String confirmLabTestAppointment(@ModelAttribute LabTestAppointmentDto dto) {
        MUser user = userService.getUserByPhone(dto.getUserPhone());
        Long patientId;
        if(user!=null) patientId = user.getId();
        else return "redirect:/lab-test-appointment-book?message=Tish phone not found";
        LabTest labTest = labTestService.getLabTestById(dto.getId());
        dto.setTestName(labTest.getTestName());
        System.out.println("patientId = "+patientId+" labTestId = "+dto.getId());
        try {
            LabTestAppointment labTestAppointment = labTestAppointmentMapper.mapToEntity(dto);
            if(labTestAppointment!=null)
            {
                labTestAppointmentService.bookNewAppointment(labTestAppointment);
                return "redirect:/lab-test-dashboard";
            }
            else return "redirect:/lab-test-appointment-book?message=upload unsuccessful";
        }catch (Exception e){
            System.out.println("Exception confirm lab test appointment post mathod = "+e.getMessage());
            return "redirect:/lab-test-dashboard";
        }
    }
    @GetMapping("/doctor-registration")
    public String showDoctorRegistrationForm(Model model) {
        model.addAttribute("doctor", new DoctorDto());
        return "DoctorRegistration";
    }
    @PostMapping("/doctor-registration")
    public String registerDoctor(@ModelAttribute("MUser") DoctorDto doctorDto,Model model) {
        if(!ImportantValidation.isValidBDPhone(doctorDto.getPhone())) return "DoctorRegistration";
        MUser user;
        if(userService.getUserByPhone(doctorDto.getPhone())==null) return "DoctorRegistration";
        user = userService.getUserByPhone(doctorDto.getPhone());
        if(doctorService.getByPhonNumber(doctorDto.getPhone())!=null) return "DoctorRegistration";
        try {
            doctorDto.setName(user.getName());
            doctorDto.setEmail(user.getEmail());
            doctorDto.setGender(user.getGender());
            doctorDto.setDateOfBirth(user.getDateOfBirth());
            Doctor doctor = doctorMapper.mapToEntity(doctorDto);
            Doctor saveDoctor = doctorService.uploadDoctor(doctor);

            return "redirect:/?message=Doctor registration successful";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "DoctorRegistration";
        }
    }

}
