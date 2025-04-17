package com.spe.appointmentservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDTO {

    private Long doctorId;
    private Long patientId;
//    private LocalDateTime appointmentDate;
}
