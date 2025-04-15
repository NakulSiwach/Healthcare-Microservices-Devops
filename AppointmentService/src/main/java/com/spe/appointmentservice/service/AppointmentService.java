package com.spe.appointmentservice.service;

import com.spe.appointmentservice.dto.AppointmentDTO;
import com.spe.appointmentservice.model.Appointment;
import com.spe.appointmentservice.model.AppointmentStatus;
import com.spe.appointmentservice.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepository;

    @Autowired
    private com.spe.appointmentservice.client.DoctorClient doctorClient;

    @Autowired
    private com.spe.appointmentservice.client.PatientClient patientClient;

    public com.spe.appointmentservice.model.Appointment createAppointment(AppointmentDTO appointmentDTO) {
        // Validate doctor and patient exist
        doctorClient.getDoctorById(appointmentDTO.getDoctorId());
        patientClient.getPatientById(appointmentDTO.getPatientId());

        // Create and save appointment
        com.spe.appointmentservice.model.Appointment appointment = Appointment.builder()
                .doctorId(appointmentDTO.getDoctorId())
                .patientId(appointmentDTO.getPatientId())
                .appointmentDate(appointmentDTO.getAppointmentDate())
                .status(AppointmentStatus.SCHEDULED)
                .build();

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public void cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(AppointmentStatus.CANCELED);
        appointmentRepository.save(appointment);
    }
}
