package com.example.javista.service.media;

import com.example.javista.dto.request.mail.MailCreationRequest;
import jakarta.mail.MessagingException;

public interface EmailService {
        void sendEmail(MailCreationRequest request, String templateName) throws MessagingException;
        boolean testSendEmail(String fullName, String email, String password);
}
