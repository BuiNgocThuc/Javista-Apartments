package com.example.javista.controller;

import org.springframework.web.bind.annotation.*;

import com.example.javista.dto.request.contact.MailCreationRequest;
import com.example.javista.service.media.EmailService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/send_email")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailController {

    EmailService emailService;

    @PostMapping
    public String sendEmail(@RequestBody MailCreationRequest request) {
        boolean result = emailService.testSendEmail(request.getFullName(), request.getEmail(), request.getPassword());
        return result ? "Email sent successfully" : "Email sent failed";
    }
}
