package com.spe.notificationservice.listener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentMessage {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    //    private LocalDateTime appointmentDate;
    private String status;
    private String patientEmail;  // new
    private String doctorEmail;
    private String doctorName;
    private String patientName;
}