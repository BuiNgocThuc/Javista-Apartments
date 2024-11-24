package com.example.javista.service.media;

import jakarta.mail.MessagingException;

import com.example.javista.dto.request.contact.MailSendRequest;

public interface EmailService {
    void sendEmail(MailSendRequest request, String templateName) throws MessagingException;

    boolean testSendEmail(String fullName, String email, String password);
}
