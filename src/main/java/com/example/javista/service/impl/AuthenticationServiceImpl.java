package com.example.javista.service.impl;

import java.text.ParseException;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.javista.dto.request.authentication.AuthenticationRequest;
import com.example.javista.dto.request.authentication.IntrospectRequest;
import com.example.javista.dto.request.authentication.LogoutRequest;
import com.example.javista.dto.request.authentication.RefreshTokenRequest;
import com.example.javista.dto.response.authentication.AuthenticationResponse;
import com.example.javista.dto.response.authentication.IntrospectResponse;
import com.example.javista.entity.InvalidatedToken;
import com.example.javista.exception.AppException;
import com.example.javista.exception.ErrorCode;
import com.example.javista.repository.InvalidatedTokenRepository;
import com.example.javista.repository.UserRepository;
import com.example.javista.service.authentication.AuthenticationService;
import com.example.javista.utils.SecurityUtils;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    SecurityUtils securityUtils;

    // compare request password with the password stored in the database
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }
        var token = securityUtils.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        boolean isValid = true;
        if (!securityUtils.verifyToken(request.getToken()) || isInvalidatedToken(request.getToken())) {
            isValid = false;
        }
        return IntrospectResponse.builder().isValid(isValid).build();
    }

    private boolean isInvalidatedToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        String jti = signedJWT.getJWTClaimsSet().getJWTID();
        if (invalidatedTokenRepository.existsById(jti)) {
            return true;
        }
        return false;
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(request.getToken());

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        String jti = signedJWT.getJWTClaimsSet().getJWTID();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jti).expiryTime(expirationTime).build();

        invalidatedTokenRepository.save(invalidatedToken);
    }

    // schedule a task to delete invalidated tokens
    @Scheduled(fixedRate = 86400000) // Run once every 24 hours
    @Transactional
    public void cleanUpExpiredTokens() {
        invalidatedTokenRepository.deleteExpiredTokens();
        System.out.println("Expired tokens cleaned up successfully.");
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(request.getToken());

        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

        invalidatedTokenRepository.save(invalidatedToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        var token = securityUtils.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }
}
