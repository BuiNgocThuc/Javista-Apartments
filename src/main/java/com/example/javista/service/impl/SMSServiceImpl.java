package com.example.javista.service.impl;

import java.util.Objects;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.javista.configuration.EsmsConfig;
import com.example.javista.dto.request.contact.SMSCreationRequest;
import com.example.javista.dto.request.contact.SMSSendRequest;
import com.example.javista.dto.response.contact.SMSResponse;
import com.example.javista.service.media.SMSService;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class SMSServiceImpl implements SMSService {

    EsmsConfig esmsConfig;
    RestTemplate restTemplate;

    @Override
    public SMSResponse sendSMS(SMSSendRequest request) {

        SMSCreationRequest smsCreationRequest = SMSCreationRequest.builder()
                .phone(request.getPhoneNumber())
                //            .content(request.getMessage())
                .content(esmsConfig.getContent())
                .apiKey(esmsConfig.getApiKey())
                .secretKey(esmsConfig.getSecretKey())
                .brandName(esmsConfig.getBrandName())
                .smsType(esmsConfig.getSmsType())
                .isUnicode(esmsConfig.getIsUnicode())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        HttpEntity<SMSCreationRequest> httpEntity = new HttpEntity<>(smsCreationRequest, headers);

        log.info("Sending SMS with payload: {}", smsCreationRequest);
        try {
            ResponseEntity<SMSResponse> response =
                    restTemplate.exchange(esmsConfig.getUrl(), HttpMethod.POST, httpEntity, SMSResponse.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info(
                        "SMS sent successfully with id: {}",
                        Objects.requireNonNull(response.getBody()).getSmsId());
                return response.getBody();
            } else {
                System.out.println("Error: " + response.getStatusCode());
                throw new Exception("Failed to send sms: "
                        + Objects.requireNonNull(response.getBody()).getSmsId());
            }

        } catch (Exception e) {
            throw new RuntimeException("Error sending SMS: " + e.getMessage(), e);
        }
    }
}
