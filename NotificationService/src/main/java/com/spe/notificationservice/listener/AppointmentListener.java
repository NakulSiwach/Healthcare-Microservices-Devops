package com.spe.notificationservice.listener;

import com.spe.appointmentservice.model.AppointmentStatus;
import com.spe.notificationservice.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "appointment.notification.queue")
    public void listenAppointment(AppointmentMessage appointment) {
        AppointmentStatus status = AppointmentStatus.valueOf(appointment.getStatus());

        String subject;
        String body;
        String to = "nakulsiwach007@gmail.com"; // Replace later with real user data

        if (status == AppointmentStatus.SCHEDULED) {
            subject = "Appointment Scheduled";
            body = "Your appointment with doctor " + appointment.getDoctorId() +
                    " is scheduled for "
//                    + appointment.getAppointmentDate()
            ;

        } else {
            subject = "Appointment Canceled";
            body = "Your appointment with doctor " + appointment.getDoctorId() +
                    " has been canceled.";
        }

        emailService.sendEmail(to, subject, body);
    }
}
