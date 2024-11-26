package com.example.javista.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
@Component
@ConfigurationProperties(prefix = "momo")
public class MomoConfig {
    String momoApiUrl;

    String secretKey;

    String accessKey;

    String returnUrl;

    String notifyUrl;

    String partnerCode;
}
