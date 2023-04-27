package com.kata.notifications.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender emailSender;

    public ResponseEntity<String> sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);

        try {
            emailSender.send(message);
            return ResponseEntity.ok("YOUR ORDER HAS BEEN PROCESSED. YOU WERE SENT AN EMAIL NOTIFICATION WITH ALL THE DETAILS. SAFE TRAVELS!");
        } catch (MailException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("FAILED TO SEND EMAIL NOTIFICATION: " + e.getMessage());
        }
    }

    public ResponseEntity<String> sendEmailNotification(String recipientEmail, String message) {
        String subject = "FLIGHT TICKET INFORMATION";
        return sendSimpleMessage(recipientEmail, subject, message);
    }
}
