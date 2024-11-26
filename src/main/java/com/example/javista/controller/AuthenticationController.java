package com.example.javista.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javista.dto.request.authentication.AuthenticationRequest;
import com.example.javista.dto.request.authentication.IntrospectRequest;
import com.example.javista.dto.request.authentication.LogoutRequest;
import com.example.javista.dto.request.authentication.RefreshTokenRequest;
import com.example.javista.dto.response.ApiResponse;
import com.example.javista.dto.response.authentication.AuthenticationResponse;
import com.example.javista.dto.response.authentication.IntrospectResponse;
import com.example.javista.service.authentication.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @Operation(summary = "Authenticate")
    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var response = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("Login Successfully")
                .result(response)
                .build();
    }

//    @PostMapping("/introspect")
//    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
//            throws ParseException, JOSEException {
//        var response = authenticationService.introspect(request);
//        return ApiResponse.<IntrospectResponse>builder()
//                .message("Introspect Successfully")
//                .result(response)
//                .build();
//    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().message("Logout Successfully").build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request)
            throws ParseException, JOSEException {
        var response = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .message("Refresh Token Successfully")
                .result(response)
                .build();
    }
}
