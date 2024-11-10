package com.example.javista.controller;

import com.example.javista.dto.request.authentication.AuthenticationRequest;
import com.example.javista.dto.request.authentication.IntrospectRequest;
import com.example.javista.dto.response.ApiResponse;
import com.example.javista.dto.response.authentication.AuthenticationResponse;
import com.example.javista.dto.response.authentication.IntrospectResponse;
import com.example.javista.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
        AuthenticationService authenticationService;

        @PostMapping("/login")
        ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
                var response = authenticationService.authenticate(request);
                return ApiResponse.<AuthenticationResponse>builder()
                        .message("Login Successfully")
                        .result(response)
                        .build();
        }

        @PostMapping("/introspect")
        ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) {
                var response = authenticationService.introspect(request);
                return ApiResponse.<IntrospectResponse>builder()
                        .message("Introspect Successfully")
                        .result(response)
                        .build();
        }
}
