package com.spe.notificationservice.listener;

import com.spe.notificationservice.model.AppointmentStatus;
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

        System.out.print(appointment.getPatientEmail());
        System.out.print(appointment.getDoctorEmail());

        String subject;
        String body;
        String to = appointment.getPatientEmail(); // Replace later with real user data

        if (status == AppointmentStatus.SCHEDULED) {
            subject = "Appointment Scheduled";
            body = "Hi " + appointment.getPatientName() + ", Your appointment with doctor :- " + appointment.getDoctorName() +
                    " , is scheduled for " + "date-time"
//                    + appointment.getAppointmentDate()
            ;
        } else {
            subject = "Appointment Canceled";
            body ="Hi " + appointment.getPatientName() +", Your appointment with doctor :- " + appointment.getDoctorName() +
                    " has been canceled.";
        }
        emailService.sendEmail(to, subject, body);


        to = appointment.getDoctorEmail();

        if (status == AppointmentStatus.SCHEDULED) {
            subject = "Appointment Scheduled";
            body = "Hi " + appointment.getDoctorName() +", Your appointment with patient :- " + appointment.getPatientName() +
                    " is scheduled for " + "date-time"
//                    + appointment.getAppointmentDate()
            ;

        } else {
            subject = "Appointment Canceled";
            body = "Hi " + appointment.getDoctorName() + "Your appointment with patient :-" + appointment.getPatientName() +
                    " has been canceled.";
        }
        emailService.sendEmail(to, subject, body);

    }
}
