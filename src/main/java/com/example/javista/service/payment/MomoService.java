package com.example.javista.service.payment;

import com.example.javista.dto.request.payment.MomoPaymentCreationRequest;
import com.example.javista.dto.response.payment.MomoPaymentResponse;
import com.example.javista.entity.Bill;

public interface MomoService {
    MomoPaymentResponse createPayment(Bill bill, MomoPaymentCreationRequest request) throws Exception;
}
