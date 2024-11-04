package com.example.javista.service.implementation;

import com.example.javista.dto.request.AuthenticationRequest;
import com.example.javista.exception.AppException;
import com.example.javista.exception.ErrorCode;
import com.example.javista.repository.UserRepository;
import com.example.javista.service.authentication.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
        UserRepository userRepository;

        // compare request password with the password stored in the database
        @Override
        public boolean authenticate(AuthenticationRequest request) {
                var user = userRepository.findByUsername(request.getUsername())
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                return passwordEncoder.matches(request.getPassword(), user.getPassword());
        }
}
