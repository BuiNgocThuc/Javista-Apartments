package com.example.javista.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
public class SecurityUtils {

        @Value("${jwt.signerKey}")
        @NonFinal
        protected static String signerKey;

        // encrypt password function
        public static String encryptPassword(String password) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                return passwordEncoder.encode(password);
        }

        // generate token function
        public static String generateToken(String username) {
                JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

                JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                        .subject(username)
                        .issuer("javista.com")
                        .issueTime(new Date())
                        .expirationTime(
                                new Date(
                                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                                )
                        )
                        .build();

                Payload payload = new Payload(jwtClaimsSet.toJSONObject());

                JWSObject jwsObject = new JWSObject(header, payload);

                try {
                        jwsObject.sign(new MACSigner(signerKey.getBytes()));

                        return jwsObject.serialize();
                } catch (JOSEException e) {
                        log.error("Error signing token", e);
                        throw new RuntimeException(e);
                }
        }

        // verify token function
        public static boolean verifyToken(String token) throws JOSEException, ParseException {
                        JWSVerifier jwsVerifier = new MACVerifier(signerKey.getBytes());

                SignedJWT signedJWT = SignedJWT.parse(token);

                Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

                var isVerified = signedJWT.verify(jwsVerifier);

                return isVerified && expirationTime.after(new Date());
        }
}
