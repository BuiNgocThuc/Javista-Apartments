package com.example.javista.service.impl;

import java.util.HashMap;
import java.util.Map;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.javista.dto.request.mail.MailCreationRequest;
import com.example.javista.service.media.EmailService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {
    JavaMailSender javaMailSender;
    SpringTemplateEngine templateEngine;

    public boolean testSendEmail(String fullName, String email, String password) {
        Map<String, Object> props = new HashMap<>();
        props.put("username", email);
        props.put("password", password);
        props.put("fullName", fullName);

        try {

            MailCreationRequest request = new MailCreationRequest();
            request.setTo(email);
            request.setSubject("Test Email");
            request.setProps(props);
            sendEmail(request, "ConfirmationEmail");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void sendEmail(MailCreationRequest request, String templateName) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

        Context context = new Context();
        context.setVariables(request.getProps());

        String html = templateEngine.process(templateName, context);

        helper.setTo(request.getTo());
        helper.setSubject(request.getSubject());
        helper.setText(html, true);

        javaMailSender.send(mimeMessage);
    }
}
