package com.example.javista.service.authentication;

import java.text.ParseException;

import com.example.javista.dto.request.authentication.AuthenticationRequest;
import com.example.javista.dto.request.authentication.IntrospectRequest;
import com.example.javista.dto.request.authentication.LogoutRequest;
import com.example.javista.dto.request.authentication.RefreshTokenRequest;
import com.example.javista.dto.response.authentication.AuthenticationResponse;
import com.example.javista.dto.response.authentication.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
}
