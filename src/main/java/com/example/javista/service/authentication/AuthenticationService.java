package com.example.javista.service.authentication;

import com.example.javista.dto.request.AuthenticationRequest;

public interface AuthenticationService {
        boolean authenticate(AuthenticationRequest request);
}
