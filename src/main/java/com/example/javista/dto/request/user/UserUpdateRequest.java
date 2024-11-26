package com.example.javista.dto.request.user;

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
public class UserUpdateRequest {
    String username;

    String password;

    String avatar;

    Boolean isFirstLogin;

    String email;

    String phone;

    UserGender gender;

    String fullName;

    String nationId;

    UserType userType;

    LocalDateTime dateOfBirth;

    Boolean isStaying;
}
