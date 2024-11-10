package com.example.javista.service.impl;

import com.example.javista.dto.request.authentication.AuthenticationRequest;
import com.example.javista.dto.request.authentication.IntrospectRequest;
import com.example.javista.dto.response.authentication.AuthenticationResponse;
import com.example.javista.dto.response.authentication.IntrospectResponse;
import com.example.javista.exception.AppException;
import com.example.javista.exception.ErrorCode;
import com.example.javista.repository.UserRepository;
import com.example.javista.service.authentication.AuthenticationService;
import com.example.javista.utils.SecurityUtils;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
        UserRepository userRepository;

        // compare request password with the password stored in the database
        @Override
        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                var user = userRepository.findByUsername(request.getUsername())
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

                if (!authenticated) {
                        throw new AppException(ErrorCode.INVALID_PASSWORD);
                }
                var token = SecurityUtils.generateToken(request.getUsername());

                return AuthenticationResponse.builder()
                        .token(token)
                        .isAuthenticated(true)
                        .build();
        }

        @Override
        public IntrospectResponse introspect(IntrospectRequest request) {
                String token = request.getToken();

                boolean isVerified = false;
                try {
                        isVerified = SecurityUtils.verifyToken(token);
                } catch (JOSEException e) {
                        throw new RuntimeException(e);
                } catch (ParseException e) {
                        throw new RuntimeException(e);
                }

                return IntrospectResponse.builder()
                        .isValid(isVerified)
                        .build();
        }
}
