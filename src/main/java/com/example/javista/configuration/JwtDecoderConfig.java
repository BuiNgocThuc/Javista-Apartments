package com.example.javista.configuration;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class JwtDecoderConfig implements JwtDecoder {
        @Value("${jwt.signerKey}")
        String signerKey;

        @Override
        public Jwt decode(String token) throws JwtException {
                return null;
        }
}
