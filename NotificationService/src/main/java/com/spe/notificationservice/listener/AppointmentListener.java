package com.spe.notificationservice.listener;

import com.spe.appointmentservice.service.AppointmentService;
import com.spe.appointmentservice.model.Appointment;
import com.spe.appointmentservice.model.AppointmentStatus;
import com.spe.notificationservice.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "appointmentQueue")
    public void listenAppointment(Appointment appointment) {
        // Check if appointment status is scheduled or canceled to trigger email notification
        if (appointment.getStatus() == AppointmentStatus.SCHEDULED) {
            String subject = "Appointment Scheduled";
            String body = "Your appointment with Dr. " + appointment.getDoctorId() + " is scheduled for " + appointment.getAppointmentDate();
            String to = "patient@example.com"; // Replace with actual patient email

            // Send email
            emailService.sendEmail(to, subject, body);
        } else if (appointment.getStatus() == AppointmentStatus.CANCELED) {
            String subject = "Appointment Canceled";
            String body = "Your appointment with Dr. " + appointment.getDoctorId() + " has been canceled.";
            String to = "patient@example.com"; // Replace with actual patient email

            // Send email
            emailService.sendEmail(to, subject, body);
        }
    }
}
