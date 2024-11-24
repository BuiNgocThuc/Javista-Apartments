package com.example.javista.configuration;

import java.time.LocalDateTime;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.javista.entity.User;
import com.example.javista.enums.UserType;
import com.example.javista.repository.UserRepository;
import com.example.javista.utils.SecurityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppConfig {

    SecurityUtils securityUtils;

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // handling ...

        return restTemplate;
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            // handling ...
            if (userRepository.findByUsername("admin").isEmpty()) {
                // create admin account
                var role = UserType.ADMIN;

                User user = User.builder()
                        .username("admin")
                        .password(securityUtils.encryptPassword("admin"))
                        .userType(role)
                        .createdAt(LocalDateTime.now())
                        .isFirstLogin(true)
                        .build();

                userRepository.save(user);
                log.info("Admin account created with default password: admin, please change it immediately");
            }
        };
    }
}
