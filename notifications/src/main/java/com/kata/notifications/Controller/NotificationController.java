package com.kata.notifications.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kata.notifications.Entity.MyRequest;
import com.kata.notifications.Service.NotificationService;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService emailService;

    @PostMapping("/notification-info")
    public ResponseEntity<String> sendNotification(@RequestBody MyRequest request) {
        // Process the request and generate a response

        // Send an email notification
        String recipientEmail = request.getRecipientEmail();
        String paymentInfo = request.getPaymentInfo();
        String message = "Here is your flight ticket information: " + paymentInfo;
        return emailService.sendEmailNotification(recipientEmail, message);
    }
}
