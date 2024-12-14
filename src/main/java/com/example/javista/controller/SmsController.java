package com.example.javista.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javista.dto.request.contact.SMSSendRequest;
import com.example.javista.dto.response.ApiResponse;
import com.example.javista.dto.response.contact.SMSResponse;
import com.example.javista.service.media.SMSService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/send_sms")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SmsController {
    SMSService smsService;

    @PostMapping
    public ApiResponse<SMSResponse> sendSms(@RequestBody SMSSendRequest request) {
        return ApiResponse.<SMSResponse>builder()
                .message("SMS sent successfully")
                .result(smsService.sendSMS(request))
                .build();
    }
}
