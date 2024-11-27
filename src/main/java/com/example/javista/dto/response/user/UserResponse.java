package com.example.javista.dto.response.user;

import java.time.LocalDateTime;

import com.example.javista.enums.UserGender;
import com.example.javista.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Integer id;

    String username;

    String avatar;

    Boolean isFirstLogin;

    String password;

    String email;

    String phone;

    UserGender gender;

    String fullName;

    String nationId;

    UserType userType;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateOfBirth;

    Boolean isStaying;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime updatedAt;
}
