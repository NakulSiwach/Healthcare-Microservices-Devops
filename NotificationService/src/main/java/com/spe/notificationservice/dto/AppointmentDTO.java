package com.spe.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long doctorId;
    private Long patientId;
    private String doctorName;
    private String patientName;
    private String patientEmail;
    private String doctorEmail;
//    private String appointmentDate;
}
