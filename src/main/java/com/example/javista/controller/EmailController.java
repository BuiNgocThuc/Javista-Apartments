package com.example.javista.controller;

import com.example.javista.dto.request.mail.MailRequest;
import com.example.javista.service.media.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test_email")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailController {

        EmailService emailService;

        @PostMapping
        public String sendEmail(@RequestBody MailRequest request) {
                boolean result = emailService.testSendEmail(request.getFullName(), request.getEmail(), request.getPassword());
                return result ? "Email sent successfully" : "Email sent failed";
        }
}
