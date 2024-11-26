package com.example.javista.dto.request.payment;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class MomoCallbackRequest {
    String orderType;

    BigDecimal amount; // Use BigDecimal for currency values

    String partnerCode;

    String orderId;

    String extraData;

    String signature;

    long transId; // This can remain as a primitive type

    long responseTime; // This can remain as a primitive type

    int resultCode; // This can remain as a primitive type

    String message;

    String payType;

    String requestId;

    String orderInfo;
}
