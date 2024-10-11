package com.example.javista.dto.request.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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

        String gender;

        String fullName;

        String nationId;

        String userType;

        LocalDateTime dateOfBirth;

        Boolean isStaying;
}
