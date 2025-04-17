package com.spe.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(toEmail);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom("nakulsiwach007work@gmail.com"); // Replace with your sender email

        try {
            mailSender.send(email);
            System.out.println("✅ Email sent to " + toEmail);
        } catch (Exception e) {
            System.err.println("❌ Error sending email: " + e.getMessage());
            e.printStackTrace();
        }

//        mailSender.send(email);
        System.out.println("✅ EEEEEEEEEEEEE" + toEmail);
    }
}
