package com.spe.appointmentservice.service;

import com.spe.appointmentservice.client.DoctorClient;
import com.spe.appointmentservice.client.PatientClient;
import com.spe.appointmentservice.dto.AppointmentDTO;
import com.spe.appointmentservice.dto.AppointmentMessage;
import com.spe.appointmentservice.dto.DoctorDTO;
import com.spe.appointmentservice.dto.PatientDTO;
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

        DoctorDTO doctor = doctorClient.getDoctorById(appointmentDTO.getDoctorId());
        PatientDTO patient = patientClient.getPatientById(appointmentDTO.getPatientId());


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
                savedAppointment.getStatus().name(),
                patient.getEmail(),
                doctor.getEmail(),
                doctor.getName(),
                patient.getName()
        );

        System.out.print(message.getDoctorEmail());
        System.out.print(message.getDoctorEmail());

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
//        if (!appointmentRepository.existsById(id)) {
//            throw new RuntimeException("Appointment not found");
//        }
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        DoctorDTO doctor = doctorClient.getDoctorById(appointment.getDoctorId());
        PatientDTO patient = patientClient.getPatientById(appointment.getPatientId());

        // Step 3: Prepare cancellation message
        AppointmentMessage message = new AppointmentMessage(
                appointment.getId(),
                appointment.getPatientId(),
                appointment.getDoctorId(),
//          appointment.getAppointmentDate(), // if used
                AppointmentStatus.CANCELED.name(),
                patient.getEmail(),
                doctor.getEmail(),
                doctor.getName(),
                patient.getName()
        );

        // Step 4: Send cancellation notification
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, message);
        System.out.println("❌ Sent appointment cancellation notification to RabbitMQ.");

        // Step 5: Delete the appointment from DB
        appointmentRepository.delete(appointment);
        System.out.println("🗑️ Appointment hard-deleted from DB.");

        appointmentRepository.deleteById(id);
//        Appointment appointment = appointmentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Appointment not found"));
//        appointment.setStatus(AppointmentStatus.CANCELED);\
//        appointmentRepository.save(appointment);
    }
}
