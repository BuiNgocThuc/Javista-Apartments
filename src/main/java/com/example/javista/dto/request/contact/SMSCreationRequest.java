package com.example.javista.dto.request.contact;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class SMSCreationRequest {

    @JsonProperty("Phone")
    String phone;

    @JsonProperty("Content")
    String content;

    @JsonProperty("ApiKey")
    String apiKey;

    @JsonProperty("SecretKey")
    String secretKey;

    @JsonProperty("Brandname")
    String brandName;

    @JsonProperty("SmsType")
    String smsType;

    @JsonProperty("IsUnicode")
    String isUnicode;
}
