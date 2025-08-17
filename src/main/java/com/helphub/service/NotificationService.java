package com.helphub.service;

import com.helphub.model.AcceptorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    // ✅ General method for sending custom emails
    public void sendEmailNotification(AcceptorRequest request, String subject, String body) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) return;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    // ✅ Called after donation is created
    public void notifyAcceptorOnDonation(AcceptorRequest request) {
        String subject = "✅ HelpHub - Your Request Was Accepted!";
        String body = "Hello " + request.getName() + ",\n\n" +
                "Your request for \"" + request.getCategories() + "\" has been accepted by a donor!\n" +
                "Stay tuned for updates.\n\n" +
                "Regards,\nHelpHub Team";
        sendEmailNotification(request, subject, body);
    }

    // ✅ Called after donation is updated
    public void notifyAcceptorOnUpdate(AcceptorRequest request) {
        String subject = "🔄 HelpHub - Donation Updated!";
        String body = "Hello " + request.getName() + ",\n\n" +
                "A donor has updated the donation related to your request for \"" + request.getCategories() + "\".\n" +
                "Please check your dashboard for updated information.\n\n" +
                "Regards,\nHelpHub Team";
        sendEmailNotification(request, subject, body);
    }

    // ✅ Called after donation is canceled
    public void notifyAcceptorOnCancel(AcceptorRequest request) {
        String subject = "❌ HelpHub - Donation Canceled";
        String body = "Hello " + request.getName() + ",\n\n" +
                "Unfortunately, a donor has canceled the donation for your request \"" + request.getCategories() + "\".\n" +
                "You may want to make the request visible again.\n\n" +
                "Regards,\nHelpHub Team";
        sendEmailNotification(request, subject, body);
    }
}
