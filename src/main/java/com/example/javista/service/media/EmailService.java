package com.example.javista.service.media;

import jakarta.mail.MessagingException;

import com.example.javista.dto.request.mail.MailCreationRequest;

public interface EmailService {
    void sendEmail(MailCreationRequest request, String templateName) throws MessagingException;

    boolean testSendEmail(String fullName, String email, String password);
}
