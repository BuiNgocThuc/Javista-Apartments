package com.example.javista.dto.request.user;

import com.example.javista.enums.UserGender;
import com.example.javista.enums.UserType;
import com.example.javista.validator.DobConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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

        Boolean isFirstLogin;

        @NotBlank(message = "EMAIL_INVALID")
        String email;

        String phone;

        UserGender gender;

        String fullName;

        String nationId;

        UserType userType;

        @DobConstraint(min = 18, message = "INVALID_DOB")
        LocalDateTime dateOfBirth;

        Boolean isStaying;
}
