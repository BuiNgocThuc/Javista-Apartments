package com.example.javista.configuration;

import java.text.ParseException;
import java.util.Objects;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import com.example.javista.dto.request.authentication.IntrospectRequest;
import com.example.javista.service.authentication.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Component
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class JwtDecoderConfig implements JwtDecoder {
    @Value("${jwt.signerKey}")
    String signerKey;

    @Autowired
    AuthenticationService authenticationService;

    NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        // introspect token
        try {
            var response = authenticationService.introspect(
                    IntrospectRequest.builder().token(token).build());
            log.info("Token introspected: {}", response.getIsValid());
            if (!response.getIsValid()) {
                throw new BadJwtException("Invalid Token");
            }
        } catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        }

        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS256");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS256)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}
