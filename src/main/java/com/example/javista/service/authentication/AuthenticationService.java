package com.example.javista.service.authentication;

import com.example.javista.dto.request.authentication.AuthenticationRequest;
import com.example.javista.dto.request.authentication.IntrospectRequest;
import com.example.javista.dto.response.authentication.AuthenticationResponse;
import com.example.javista.dto.response.authentication.IntrospectResponse;

public interface AuthenticationService {
        AuthenticationResponse authenticate(AuthenticationRequest request);
        IntrospectResponse introspect(IntrospectRequest request);
}
