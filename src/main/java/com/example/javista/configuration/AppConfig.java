package com.example.javista.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

        @Bean
        public ObjectMapper objectMapper() {
                ObjectMapper objectMapper = new ObjectMapper();
                // handling ...

                return objectMapper;
        }

        @Bean
        public RestTemplate restTemplate() {
                RestTemplate restTemplate = new RestTemplate();
                // handling ...

                return restTemplate;
        }
}
