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
@ConfigurationProperties(prefix = "esms")
public class EsmsConfig {
    String apiKey;

    String secretKey;

    String brandName;

    String content;

    String smsType;

    String url;

    String isUnicode;
}
