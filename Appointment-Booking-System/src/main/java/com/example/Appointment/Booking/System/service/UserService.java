package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.CountAppointment;
import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.model.entity.DoctorAppointment;
import com.example.Appointment.Booking.System.model.entity.MUser;
import com.example.Appointment.Booking.System.repository.CountAppointmentRepository;
import com.example.Appointment.Booking.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CountAppointmentRepository countAppointmentRepository;
    private final DoctorAppointmentService doctorAppointmentService;

    public MUser saveNewUser(MUser user){
        if(userRepository.findByPhonNumber(user.getPhonNumber()).isPresent()
        || userRepository.findByEmail(user.getEmail()).isPresent()) return null;
        try {
            return userRepository.save(user);
        }catch (Exception e){
            System.out.println("save user Error : "+e.getMessage());
            return null;
        }
    }
    public MUser getUserById(Long id){
        return userRepository.findById(id).get();
    }
    public MUser getUserByPhone(String phone){
        return userRepository.findByPhonNumber(phone).orElse(null);
    }
    // book
    public String bookDoctor(Long doctorId, Long patientId){
        CountAppointment countAppointment = countAppointmentRepository.findByDoctorId(doctorId);
        if(countAppointment == null){
            return "This doctor are not available write now";
        }
        else if(countAppointment.getTotalPatient()<=0){
            return "all appointment is booked. try another one";
        }
        else{
            //DoctorAppointment doctorAppointment = new DoctorAppointment();
            try {
                doctorAppointmentService.addDoctorAppointment(doctorId,patientId,countAppointment.getTotalPatient());
                countAppointment.setTotalPatient(countAppointment.getTotalPatient()-1);
                countAppointmentRepository.save(countAppointment);
                return "doctor appointment booked";
            }catch (Exception e){
                return "doctor appointment not booked";
            }
        }
    }
}
