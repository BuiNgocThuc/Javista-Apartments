package com.example.javista.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUtils {
        // encrypt password function
        public static String encryptPassword(String password) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                return passwordEncoder.encode(password);
        }
}
