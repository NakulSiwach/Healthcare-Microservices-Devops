package com.spe.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;



public class AppointmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long doctorId;
    private Long patientId;
    private String doctorName;
    private String patientName;
    private String patientEmail;
    private String appointmentDate;

    // Default constructor
    public AppointmentDTO() {}

    // Parameterized constructor
    public AppointmentDTO(Long doctorId, Long patientId, String doctorName, String patientName, String patientEmail, String appointmentDate) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.appointmentDate = appointmentDate;
    }

    // Getters and Setters
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientEmail='" + patientEmail + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                '}';
    }
}
