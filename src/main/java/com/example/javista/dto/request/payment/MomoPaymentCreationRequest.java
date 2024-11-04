package com.example.javista.dto.request.payment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class MomoPaymentCreationRequest {
        @NotNull(message = "RequestType is required.")
        @Pattern(regexp = "^(captureWallet|payWithATM|payWithCC)$",
                message = "Invalid RequestType. Allowed values are captureWallet, " +
                        " payWithATM, payWithCC.")
        String requestType;
}
