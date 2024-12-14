package com.example.javista.service.media;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.javista.dto.request.contact.SMSSendRequest;
import com.example.javista.dto.response.contact.SMSResponse;

public interface SMSService {
    @PreAuthorize("hasRole('ADMIN')")
    SMSResponse sendSMS(SMSSendRequest request);
}
