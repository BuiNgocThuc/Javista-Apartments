package com.example.javista.dto.request.user;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.example.javista.enums.UserGender;
import com.example.javista.enums.UserType;
import com.example.javista.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 6, message = "Username must be at least 6 characters")
    @NotNull
    String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    @NotNull
    String password;

    String avatar;

    @NotNull
    Boolean isFirstLogin;

    @NotNull(message = "EMAIL_INVALID")
    String email;

    @NotNull
    String phone;

    @NotNull
    UserGender gender;

    @NotNull
    String fullName;

    @NotNull
    String nationId;

    @NotNull
    UserType userType;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    @NotNull
    LocalDateTime dateOfBirth;

    @NotNull
    Boolean isStaying;
}
