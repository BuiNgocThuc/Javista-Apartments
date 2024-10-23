package com.example.javista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JavistaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavistaApplication.class, args);
    }

}
