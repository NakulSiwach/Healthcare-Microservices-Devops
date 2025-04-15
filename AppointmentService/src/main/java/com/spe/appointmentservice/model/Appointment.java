package com.spe.appointmentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorId; // Foreign key reference to Doctor microservice
    private Long patientId; // Foreign key reference to Patient microservice

    private LocalDateTime appointmentDate; // Date and time of the appointment

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status; // Status of the appointment (e.g., SCHEDULED, CANCELED, COMPLETED)
}
