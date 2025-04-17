package com.spe.appointmentservice.service;

import com.spe.appointmentservice.client.DoctorClient;
import com.spe.appointmentservice.client.PatientClient;
import com.spe.appointmentservice.dto.AppointmentDTO;
import com.spe.appointmentservice.dto.AppointmentMessage;
import com.spe.appointmentservice.model.Appointment;
import com.spe.appointmentservice.model.AppointmentStatus;
import com.spe.appointmentservice.repository.AppointmentRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.spe.appointmentservice.config.RabbitMQConfig.EXCHANGE;
import static com.spe.appointmentservice.config.RabbitMQConfig.ROUTING_KEY;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepository;

    @Autowired
    private DoctorClient doctorClient;

    @Autowired
    private PatientClient patientClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Appointment createAppointment(AppointmentDTO appointmentDTO) {
        // Validate doctor and patient exist
        doctorClient.getDoctorById(appointmentDTO.getDoctorId());
        patientClient.getPatientById(appointmentDTO.getPatientId());

        // Create and save appointment
        Appointment appointment = Appointment.builder()
                .doctorId(appointmentDTO.getDoctorId())
                .patientId(appointmentDTO.getPatientId())
//                .appointmentDate(appointmentDTO.getAppointmentDate())
                .status(AppointmentStatus.SCHEDULED)
                .build();

        Appointment savedAppointment = appointmentRepository.save(appointment);

        AppointmentMessage message = new AppointmentMessage(
                savedAppointment.getId(),
                savedAppointment.getPatientId(),
                savedAppointment.getDoctorId(),
//                savedAppointment.getAppointmentDate(),
                savedAppointment.getStatus().name()
        );

        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, message);
        System.out.println("✅ Sent appointment notification to RabbitMQ.");

        return savedAppointment;
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
