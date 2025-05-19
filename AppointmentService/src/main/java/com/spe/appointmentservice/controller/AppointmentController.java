package com.spe.appointmentservice.controller;

import com.spe.appointmentservice.dto.AppointmentDTO;
import com.spe.appointmentservice.model.Appointment;
import com.spe.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Appointment createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.createAppointment(appointmentDTO);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

    @DeleteMapping("/{id}/cancel")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "Appointment canceled successfully!";
    }
}
