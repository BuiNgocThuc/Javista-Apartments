package com.example.javista.dto.response.user;

import java.time.LocalDateTime;

import com.example.javista.enums.UserGender;
import com.example.javista.enums.UserType;

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

    LocalDateTime dateOfBirth;

    Boolean isStaying;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
