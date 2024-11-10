package com.example.javista.service.impl;

import com.example.javista.configuration.MomoConfig;
import com.example.javista.dto.request.payment.ExtraData;
import com.example.javista.dto.request.payment.MomoDataRequest;
import com.example.javista.dto.request.payment.MomoPaymentCreationRequest;
import com.example.javista.dto.response.payment.MomoPaymentResponse;
import com.example.javista.entity.Bill;
import com.example.javista.service.payment.MomoService;
import com.example.javista.utils.CreateSignature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class MomoServiceImpl implements MomoService {
        MomoConfig momoConfig;
        RestTemplate restTemplate;
        ObjectMapper objectMapper;
        CreateSignature createSignature;

        @Override
        public MomoPaymentResponse createPayment(Bill bill, MomoPaymentCreationRequest request)
        throws Exception{
                System.out.println("Creating Momo payment...");

                String billInfo = "Payment for the bill " + bill.getMonthly();
                String requestId = UUID.randomUUID().toString();
                // get current milliseconds
                String orderId = System.currentTimeMillis() + "_" + bill.getId();
                String notifyUrl = momoConfig.getNotifyUrl().replace("{{id}}", String.valueOf(bill.getId()));

                // Create extraData
                String extraData = Base64.getEncoder().encodeToString(objectMapper.writeValueAsBytes(new ExtraData(bill.getId())));

                // Build rawData
                String rawData = String.format("accessKey=%s&amount=%.0f&extraData=%s&ipnUrl=%s" +
                                "&orderId=%s&orderInfo=%s&partnerCode=%s" +
                                "&redirectUrl=%s&requestId=%s&requestType=%s",
                        momoConfig.getAccessKey(),
                        bill.getTotalPrice(),
                        extraData,
                        notifyUrl,
                        orderId,
                        billInfo,
                        momoConfig.getPartnerCode(),
                        momoConfig.getReturnUrl(),
                        requestId,
                        request.getRequestType());

                // Create signature
                String signature = createSignature.computeHmacSha256(rawData, momoConfig.getSecretKey());

                // Build request data
                MomoDataRequest requestData = new MomoDataRequest(
                        momoConfig.getPartnerCode(),
                        requestId, bill.getTotalPrice(),
                        orderId,
                        billInfo,
                        momoConfig.getReturnUrl(),
                        notifyUrl,
                        "vi",
                        15,
                        extraData,
                        request.getRequestType(),
                        signature,
                        true);

                // Prepare HTTP request
                HttpHeaders headers = new HttpHeaders();
                // set content type JSON
                headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
                // Create the HTTP entity with the request data and headers
                HttpEntity<MomoDataRequest> httpEntity = new HttpEntity<>(requestData, headers);

                System.out.println(momoConfig.getNotifyUrl());
                try {
                        // Make the HTTP POST request to the Momo API and capture the response
                        ResponseEntity<MomoPaymentResponse> response = restTemplate.exchange(
                                momoConfig.getMomoApiUrl(),
                                HttpMethod.POST,
                                httpEntity,
                                MomoPaymentResponse.class
                        );

                        if (response.getStatusCode().is2xxSuccessful()) {
                                return response.getBody();
                        } else {
                                System.out.println("Error: " + response.getStatusCode());
                                throw new Exception("Failed to create Momo payment: " + Objects.requireNonNull(response.getBody()).getMessage());
                        }
                } catch (Exception ex) {
                        System.out.println("Exception: " + ex.getMessage());
                        throw ex;
                }
        }
}
