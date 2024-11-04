package com.example.javista.dto.response.payment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class MomoPaymentResponse {
        private String requestId;

        private int errorCode; // This can remain as a primitive type since it can be 0

        private String orderId;

        private String message;

        private String payUrl;
}
