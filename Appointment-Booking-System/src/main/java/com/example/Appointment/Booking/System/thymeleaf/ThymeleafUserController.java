package com.example.Appointment.Booking.System.thymeleaf;

import com.example.Appointment.Booking.System.dummyData.UploadSomeData;
import com.example.Appointment.Booking.System.jwt.JwtUtils;
import com.example.Appointment.Booking.System.model.dto.*;
import com.example.Appointment.Booking.System.model.entity.*;
import com.example.Appointment.Booking.System.model.mapper.DoctorMapper;
import com.example.Appointment.Booking.System.model.mapper.LabTestAppointmentMapper;
import com.example.Appointment.Booking.System.model.mapper.MUserMapper;
import com.example.Appointment.Booking.System.repository.*;
import com.example.Appointment.Booking.System.service.*;
import com.example.Appointment.Booking.System.validation.ImportantValidation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class ThymeleafUserController {

    private final UserService userService;
    private final MUserMapper userMapper;
    private final DoctorService doctorService;
    private final LabTestService labTestService;
    private final DoctorAppointmentService doctorAppointmentService;
    private final AuthenticationService authenticationService;
    private final LabTestAppointmentService labTestAppointmentService;
    private final LabTestAppointmentMapper labTestAppointmentMapper;
    private final DoctorMapper doctorMapper;
    private final JwtUtils jwtUtils;
    private final UploadSomeData uploadSomeData;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final AppointmentSlotRepository slotRepository;
    private final DoctorAppointmentHistoryRepository doctorAppointmentHistoryRepository;

    private String getJwtFromCookies(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

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
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())) return "redirect:/registration?message=Passwords do not match";
        else if(!ImportantValidation.isValidBDPhone(userDto.getPhonNumber())) return "redirect:/registration?message=Invalid phone number";
        else if(userDto.getEmail()!=null && !ImportantValidation.isValidEmail(userDto.getEmail())) return "redirect:/registration?message=Invalid email";
        else
        {
            try {
                MUser user = userMapper.mapToEntity(userDto);
                authenticationService.sinUp(user);
                return "redirect:/login?message=Registration successful";

            }catch (Exception e){
                System.out.println("Exception = "+e.getMessage());
                return "redirect:/registration?message=Registration failed";
            }
        }
    }
    @GetMapping("/login-with-dummy-data")
    public String loginWithDummyData(){
        uploadSomeData.uploadSomeUser();
        uploadSomeData.uploadSomeLabTest();
        return "redirect:/login?message=Dummy data uploaded";
    }
    @GetMapping("/login")         //-------------------------Login-------------------
    public String loginPage(Model model, HttpServletRequest request){
        model.addAttribute("login_request", new SignInRequestDto());
        String token = getJwtFromCookies(request);
        if(token!=null){
            try {
                String phone = jwtUtils.extractUsername(token);
                MUser user = userService.getUserByPhone(phone);
                if(user==null)
                {
                    return "redirect:/user-logout?message=User not found. Please check your phone and try again.";
                }
                else {
                    //Extract role from token
                    List<String> roles = jwtUtils.extractRoles(token);

                    //Role-based redirect
                    if (roles.contains("ADMIN")) {
                        return "redirect:/admin/dashboard";
                    } else if (roles.contains("DOCTOR")) {
                        return "redirect:/doctor/panel";
                    } else if (roles.contains("USER")) {
                        return "redirect:/lab-test-dashboard";
                    } else {
                        return "redirect:/login?message=Invalid token";
                    }
                }
            }catch (Exception e){
                System.out.println("Exception = "+e.getMessage());
                return "redirect:/login?message=Login failed. Please check your phone and password, try again.";
            }
        }
        return "Login";
    }
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("login_request") SignInRequestDto signInRequestDto, HttpServletResponse response)
    {
        try {
            JwtAuthenticationResponseDto status = authenticationService.signIn(signInRequestDto);
            if (status.getToken() != null) {
                // Set JWT in HTTP-only cookie
                ResponseCookie cookie = ResponseCookie.from("jwt", status.getToken())
                        .httpOnly(true)
                        .path("/")
                        .maxAge(24 * 60 * 60)
                        .build();

                response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

                //Extract role from token
                List<String> roles = jwtUtils.extractRoles(status.getToken());

                //Role-based redirect
                if (roles.contains("ADMIN")) {
                    return "redirect:/admin/dashboard";
                } else if (roles.contains("DOCTOR")) {
                    return "redirect:/doctor/panel";
                } else if (roles.contains("USER")) {
                    return "redirect:/lab-test-dashboard";
                } else {
                    return "redirect:/login?message=Invalid token";
                }
            }
            else return "redirect:/login?message=Login failed. Please check your phone and password, try again.";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/login?message=Login failed. Please check your phone and password, try again.";
        }
    }

    @GetMapping("/user-logout")
    public String logout(HttpServletResponse response) {
        // remove JWT from cookie
        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .path("/")
                .maxAge(0)  //  give 0 then cookie will be deleted immediately
                .sameSite("Lax")
                .secure(false)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return "redirect:/login?message=You are logged out";
    }

    @GetMapping("/lab-test-dashboard")     //-------------------------Lab Test Dashboard ------------------------
    public String labTestDashboard(@RequestParam(value = "search", required = false) String search,Model model,HttpServletRequest request){
        List<LabTest> allLabTestList;
        if (search != null && !search.isEmpty()) allLabTestList = labTestService.getLabTestContain(search);
        else allLabTestList = labTestService.getAllLabTest();
        try {
            model.addAttribute("allTest",allLabTestList);
            String token = getJwtFromCookies(request);
            String phone = jwtUtils.extractUsername(token);
            MUser user = userService.getUserByPhone(phone);
            model.addAttribute("username",user.getName());
            model.addAttribute("TestAppointmentHistory",labTestAppointmentService.getOneUserHistory(user.getId()));
            return "LabTestDashBoard";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/login";
        }
    }
    @GetMapping("/doctor-dashboard")     //-------------------------Doctor Dashboard ------------------------
    public String doctorDashboard(@RequestParam(value = "search", required = false) String search,Model model,HttpServletRequest request){
        List<Doctor> allDoctorList;

        if (search != null && !search.isEmpty()) allDoctorList = doctorService.getDoctorByNameLike(search);
        else allDoctorList = doctorService.getAllDoctors();
        //List<Doctor> allDoctorList = doctorService.getAllDoctors();
        List<DoctorAvailableStatusDto> allDoctorWithStatus = new ArrayList<>();
        for(Doctor doctor:allDoctorList){
            DoctorAvailableStatusDto doctorAvailableStatusDto = new DoctorAvailableStatusDto();
            doctorAvailableStatusDto.setId(doctor.getId());
            doctorAvailableStatusDto.setName(doctor.getName());
            doctorAvailableStatusDto.setQualification(doctor.getQualification());
            doctorAvailableStatusDto.setSpecialization(doctor.getSpecialization());
            allDoctorWithStatus.add(doctorAvailableStatusDto);
        }
        model.addAttribute("doctors",allDoctorWithStatus);
        // get cookies
        try {
            String token = getJwtFromCookies(request);
            String phone = jwtUtils.extractUsername(token);
            MUser user = userService.getUserByPhone(phone);
            model.addAttribute("username",user.getName());
            model.addAttribute("appointmentHistory",doctorAppointmentService.getHistoryByPatientId(user.getId()));
            return "DoctorDashBoard";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/login";
        }
    }
    @GetMapping("/doctor-appointment-book/{doctorId}")  //---------------Doctor Appointment confirm ----------------
    public String showDoctorAppointmentForm(@PathVariable Long doctorId,@RequestParam(required = false) LocalDate date, Model model, HttpServletRequest request) {
        try {
            //find user from cookies
            String token = getJwtFromCookies(request);
            String phone = jwtUtils.extractUsername(token);
            Long patientId = jwtUtils.extractUserId(token);

            Doctor doctor = doctorService.findDoctorById(doctorId);
            model.addAttribute("doctor", doctor);

            // create dto and set doctor id
            DoctorAppointmentConfirmDto dto = new DoctorAppointmentConfirmDto();
            dto.setDoctorId(doctorId);
            dto.setPatientId(patientId);
            dto.setUserPhone(phone);
            System.out.println("Today = "+LocalDate.now());

            LoadFormDto loadFormDto = new LoadFormDto();
            if(date!=null) {
                loadFormDto.setDate(date);
                System.out.println("date = "+date);
            }
            else loadFormDto.setDate(LocalDate.now());
            model.addAttribute("doctorAppointmentConfirmDto", dto);
            model.addAttribute("loadFrom", loadFormDto);
            // Step 1: Check if doctor is available that day
            Optional<DoctorAvailability> availability = doctorAvailabilityRepository
                    .findByDoctorIdAndDate(doctorId, date);

            if (availability.isPresent() && !availability.get().isAvailable()) {
                return "redirect:/doctor-appointment-book/" + doctorId + "?date=" + date + "&message=Doctor is not available on this date.";
            }

            // Step 2: Load available slots
            List<AppointmentSlot> slots = slotRepository.findByDoctorIdAndDateAndBookedFalse(doctorId, date);
            List<TimeSelectDto> times = new ArrayList<>();
            for(AppointmentSlot slot:slots){
                TimeSelectDto timeAndSlot = new TimeSelectDto();
                timeAndSlot.setTime(slot.getStartTime());
                timeAndSlot.setSlotId(slot.getId());
                times.add(timeAndSlot);
            }
            model.addAttribute("times",times);
            return "DoctorAppointmentForm";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/login";
        }
    }
    @PostMapping("/doctor-appointment-book/reload")
    public String reloadDoctorAppointmentForm(@ModelAttribute LoadFormDto loadFormDto) {
        Long doctorId = loadFormDto.getDoctorId();
        LocalDate date = loadFormDto.getDate();
        String redirectUrl = "/doctor-appointment-book/" + doctorId;
        if (date != null) {
            redirectUrl += "?date=" + date;
        }
        return "redirect:" + redirectUrl;
    }


    @PostMapping("/doctor-appointment-book/confirm")
    public String confirmDoctorAppointment(@ModelAttribute DoctorAppointmentConfirmDto dto) {
        System.out.println("selected time = "+dto.getSelectedTimeSlotId());
        System.out.println("Appointment confirm dto = "+dto);
        // booking slot
        AppointmentSlot slot = slotRepository.findById(dto.getSelectedTimeSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        // Check doctor availability on that day
        Optional<DoctorAvailability> availability = doctorAvailabilityRepository
                .findByDoctorIdAndDate(slot.getDoctorId(), slot.getDate());

        if (availability.isPresent() && !availability.get().isAvailable()) {
            return "redirect:/doctor-appointment-book/" + dto.getDoctorId() + "?date=" + slot.getDate() + "&message=Doctor is not available on this date.";
        }
        if (slot.isBooked()) {
            return "redirect:/doctor-appointment-book/" + dto.getDoctorId() + "?date=" + slot.getDate() + "&message=Slot already booked!";
        }
        slot.setBooked(true);
        slot.setPatientId(dto.getPatientId());
        slotRepository.save(slot);
        // save appointment history
        DoctorAppointmentHistory history = new DoctorAppointmentHistory();
        history.setPatientId(dto.getPatientId());
        history.setBookingDate(LocalDate.now());
        history.setAppointmentDate(slot.getDate());
        history.setStatus("Confirmed");
        Doctor doctor = doctorService.findDoctorById(dto.getDoctorId());
        history.setDoctorName(doctor.getName());
        history.setDoctorSpecialization(doctor.getSpecialization());
        //System.out.println("Appointment date = "+slot.getDate());
        String historySaveStatus = doctorAppointmentService.saveAppointmentHistory(history);

        return "redirect:/doctor-dashboard?message=Doctor appointment booked successfully!"+historySaveStatus;

    }
    @GetMapping("/lab-test-appointment-book/{labTestId}")   //-------------------Lab test appointment confirm-------------
    public String showLabTestAppointmentForm(@PathVariable Long labTestId, Model model, HttpServletRequest request) {

        try {
            //find user from cookies
            String token = getJwtFromCookies(request);
            String phone = jwtUtils.extractUsername(token);
            MUser user = userService.getUserByPhone(phone);

            LabTest labTest = labTestService.getLabTestById(labTestId);
            model.addAttribute("labTest", labTest);

            LabTestAppointmentDto dto = new LabTestAppointmentDto();
            dto.setId(labTestId);
            dto.setTestName(labTest.getTestName());
            dto.setUserId(user.getId());

            // load lab details
            Set<Lab> labList = labTest.getLabs();
            model.addAttribute("labList", labList);

            model.addAttribute("labTestAppointmentDto", dto);
            return "LabTestAppointmentForm";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/login";
        }

    }
    @PostMapping("/lab-test-appointment-book/confirm")
    public String confirmLabTestAppointment(@ModelAttribute LabTestAppointmentDto dto) {
        LabTest labTest = labTestService.getLabTestById(dto.getId());
        dto.setTestName(labTest.getTestName());
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
    @GetMapping("/user-history")  //-------------------------- Show User History---------------------------
    public String userHistory(Model model,HttpServletRequest request){
        try {
            String token = getJwtFromCookies(request);
            String phone = jwtUtils.extractUsername(token);
            MUser user = userService.getUserByPhone(phone);
            model.addAttribute("username",user.getName());
            model.addAttribute("DoctorAppointmentHistory",doctorAppointmentService.getHistoryByPatientId(user.getId()));
            model.addAttribute("TestAppointmentHistory",labTestAppointmentService.getOneUserHistory(user.getId()));
            return "UserHistory";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/login";
        }
    }

}
