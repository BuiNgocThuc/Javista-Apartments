package com.example.javista.dto.response.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class MomoPaymentResponse {
    String requestId;

    int errorCode; // This can remain as a primitive type since it can be 0

    String orderId;

    String message;

    String payUrl;
}
