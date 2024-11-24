package com.example.javista.configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.javista.entity.Relationship;
import com.example.javista.enums.RelationshipRole;
import com.example.javista.repository.RelationshipRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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
    ApplicationRunner applicationRunner(UserRepository userRepository, Environment env, RelationshipRepository relationshipRepository) {
        return args -> {
            String defaultAdminUsername = env.getProperty("app.default.admin.username");
            String defaultAdminPassword = env.getProperty("app.default.admin.password");
            String defaultUserPassword = env.getProperty("app.default.resident.password");

            // handling ...
            if (userRepository.findByUsername("admin").isEmpty()) {
                // create admin account
                User user = User.builder()
                    .username(env.getProperty(defaultAdminUsername))
                    .password(securityUtils.encryptPassword(defaultAdminPassword))
                    .userType(UserType.ADMIN)
                    .createdAt(LocalDateTime.now())
                    .isFirstLogin(true)
                    .build();

                userRepository.save(user);
                log.info("Admin account created with default password: admin, please change it immediately");
            }
        };
    }
}
