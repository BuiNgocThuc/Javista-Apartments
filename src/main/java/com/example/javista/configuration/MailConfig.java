package com.example.javista.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Configuration
public class MailConfig {
        @Value("${spring.mail.host}")
        String host;

        @Value("${spring.mail.port}")
        Integer port;

        @Value("${spring.mail.username}")
        String email;

        @Value("${spring.mail.password}")
        String password;

        @Bean
        public JavaMailSender javaMailSender() {
                JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
                mailSender.setHost(host);
                mailSender.setPort(port);
                mailSender.setUsername(email);
                mailSender.setPassword(password);
                mailSender.setDefaultEncoding("UTF-8");

                Properties props = mailSender.getJavaMailProperties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.auth", true);
                props.put("mail.smtp.starttls.enable", true);
                props.put("mail.smtp.ssl.enable", false);
                props.put("mail.smtp.from", email);
                props.put("mail.debug", "true");

                return mailSender;
        }
}
