package com.example.Appointment.Booking.System.thymeleaf;

import com.example.Appointment.Booking.System.model.dto.LabDto;
import com.example.Appointment.Booking.System.model.dto.LabTestDto;
import com.example.Appointment.Booking.System.model.dto.TestTypeDto;
import com.example.Appointment.Booking.System.model.entity.Lab;
import com.example.Appointment.Booking.System.model.entity.LabTest;
import com.example.Appointment.Booking.System.model.entity.TestType;
import com.example.Appointment.Booking.System.model.mapper.LabMapper;
import com.example.Appointment.Booking.System.model.mapper.LabTestMapper;
import com.example.Appointment.Booking.System.model.mapper.TestTypeMapper;
import com.example.Appointment.Booking.System.service.LabService;
import com.example.Appointment.Booking.System.service.TestTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/dashboard")
    public String adminPage(){
        return "AdminPage";
    }

    @GetMapping("add-new-test-type")
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
}
