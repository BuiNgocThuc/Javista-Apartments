package com.example.javista.service.media;


import com.example.javista.dto.request.contact.SMSSendRequest;
import com.example.javista.dto.response.contact.SMSResponse;
import org.springframework.security.access.prepost.PreAuthorize;

public interface SMSService {
    @PreAuthorize("hasRole('ADMIN')")
    SMSResponse sendSMS(SMSSendRequest request);
}
