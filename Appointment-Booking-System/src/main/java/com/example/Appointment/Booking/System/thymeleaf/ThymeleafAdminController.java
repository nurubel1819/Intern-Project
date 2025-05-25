package com.example.Appointment.Booking.System.thymeleaf;

import com.example.Appointment.Booking.System.jwt.JwtUtils;
import com.example.Appointment.Booking.System.model.dto.*;
import com.example.Appointment.Booking.System.model.entity.*;
import com.example.Appointment.Booking.System.model.mapper.*;
import com.example.Appointment.Booking.System.service.*;
import com.example.Appointment.Booking.System.validation.ImportantValidation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ThymeleafAdminController {

    private final LabTestMapper labTestMapper;
    private final TestTypeService testTypeService;
    private final LabService labService;
    private final LabMapper labMapper;
    private final LabService labService1;
    private final TestTypeService testTypeService1;
    private final TestTypeMapper testTypeMapper;
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final UserService userService;
    private final MUserMapper userMapper;
    private final RoleService roleService;
    private final JwtUtils jwtUtil;

    @GetMapping("/dashboard")
    public String adminPage(Model model, HttpServletRequest request){
        try {
            String jwt = jwtUtil.getJwtFromCookies(request);
            Long id = jwtUtil.extractUserId(jwt);
            MUser user = userService.getUserById(id);
            String username = user.getName();
            model.addAttribute("username",username);
            return "AdminPage";
        }catch (Exception e){
            return "redirect:/login?message=Error in getting user details";
        }
    }

    @GetMapping("add-new-test-type")  //--------------------Upload New Test Type--------------------
    public String addNewTestType(Model model){
        model.addAttribute("testTypeDto", new TestTypeDto());
        return "TestTypePage";
    }
    @PostMapping("add-new-test-type")
    public String saveTestType(@ModelAttribute TestTypeDto testTypeDto){
        try {
            TestType testType = testTypeMapper.mapToEntity(testTypeDto);
            testTypeService1.uploadTestType(testType);
            return "redirect:/admin/dashboard?message=upload successful";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/add-new-test-type?message=upload failed";
        }
    }

    @GetMapping("/add-new-lab-or-hospital") //--------------------Upload Lab or Hospital--------------------
    public String addNewLabOrHospital(Model model){
        model.addAttribute("labDto", new LabDto());
        return "LabPage";
    }
    @PostMapping("/add-new-lab-or-hospital")
    public String saveLab(@ModelAttribute LabDto labDto){
        try {
            Lab lab = labMapper.mapToEntity(labDto);
            labService1.uploadLabDetails(lab);
            return "redirect:/admin/dashboard?message=upload successful";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/add-new-lab-or-hospital?message=upload failed";
        }
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
            return "redirect:/admin/dashboard?message=upload successful";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/upload_new_test?message=upload unsuccessful";
        }
    }
    @GetMapping("/doctor-registration")  //-------------------------Doctor Registration-----------------------
    public String showDoctorRegistrationForm(Model model) {
        model.addAttribute("doctor", new DoctorDto());
        return "DoctorRegistration";
    }
    @PostMapping("/doctor-registration")
    public String registerDoctor(@ModelAttribute("MUser") DoctorDto doctorDto,Model model) {
        if(!ImportantValidation.isValidBDPhone(doctorDto.getPhone())) return "redirect:/admin/doctor-registration?message=invalid phone number";
        MUser user;
        if(userService.getUserByPhone(doctorDto.getPhone())==null) return "redirect:/admin/doctor-registration?message=User Registration first then doctor registration";
        user = userService.getUserByPhone(doctorDto.getPhone());
        if(doctorService.getByPhonNumber(doctorDto.getPhone())!=null) return "redirect:/admin/doctor-registration?message=Phone or email already exists in database";
        try {
            doctorDto.setName(user.getName());
            doctorDto.setEmail(user.getEmail());
            doctorDto.setGender(user.getGender());
            doctorDto.setDateOfBirth(user.getDateOfBirth());
            Doctor doctor = doctorMapper.mapToEntity(doctorDto);
            doctorService.uploadDoctor(doctor);
            return "redirect:/admin/dashboard?message=Doctor registration successful";
        }catch (Exception e){
            System.out.println("Exception = "+e.getMessage());
            return "redirect:/admin/doctor-registration?message=Doctor registration failed";
        }
    }
    @GetMapping("/see-all-user")  //-------------------------See All User---------------------------
    public String seeAllUser(Model model){
        List<MUser> allUser = userService.getAllUsers();
        List<MUserDto> allUserDto = new ArrayList<>();
        for(MUser user:allUser){
            allUserDto.add(userMapper.mapToDto(user));
        }
        model.addAttribute("allUserDto",allUserDto);
        return "AllUserInfo";
    }
    @GetMapping("/single-user-details/userID/{id}")
    public String singleUserDetails(@PathVariable("id") Long userId, Model model){
        try {
            MUser user = userService.getUserById(userId);
            model.addAttribute("userInformation",user);
            List<String> userRoles = new ArrayList<>();
            for(UserRole role : user.getUserRoles()){
                userRoles.add(role.getRole());
            }
            model.addAttribute("userRoles",userRoles);
            List<String> AllSystemRole = new ArrayList<>();
            for(UserRole role : roleService.getAllRoles()){
                AllSystemRole.add(role.getRole());
            }
            model.addAttribute("allSystemRole",AllSystemRole);
            RoleSetDto roleSetDto = new RoleSetDto();
            roleSetDto.setUserId(userId);
            model.addAttribute("roleSetDto",roleSetDto);
            return "SingleUserInformation";
        }catch (Exception e){
            return "redirect:/admin/dashboard?message=User not found";
        }
    }
    @PostMapping("/set_user_role")
    public String setUserRole(@ModelAttribute RoleSetDto roleSetDto){
        try {
            Long roleId = roleService.findRoleByName(roleSetDto.getRoleName()).getId();
            roleService.setUserRole(roleSetDto.getUserId(),roleId);
            return "redirect:/admin/single-user-details/userID/" + roleSetDto.getUserId() + "?message=Role set successfully";
        }catch (Exception e){
            return "redirect:/admin/dashboard?message=Role set failed";
        }
    }
    @GetMapping("/add-new-role")
    public String addNewRole(Model model){
        model.addAttribute("roleAddDto", new RoleAddDto());
        List<String> AllSystemRole = new ArrayList<>();
        for(UserRole role : roleService.getAllRoles()){
            AllSystemRole.add(role.getRole());
        }
        model.addAttribute("allSystemRole",AllSystemRole);
        return "AddNewRole";
    }
    @PostMapping("/add-new-role")
    public String addNewRoleConfirm(@ModelAttribute RoleAddDto roleAddDto){
        roleAddDto.setRoleName(roleAddDto.getRoleName().toUpperCase());
        if(roleService.findRoleByName(roleAddDto.getRoleName()) == null){
            roleService.addNewRole(roleAddDto.getRoleName());
            return "redirect:/admin/dashboard?message=Role added successfully";
        }else{
            return "redirect:/admin/add-new-role?message=Role already exists";
        }
    }
}
