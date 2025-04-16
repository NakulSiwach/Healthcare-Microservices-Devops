package com.spe.appointmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentMessage {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private String status;
}
