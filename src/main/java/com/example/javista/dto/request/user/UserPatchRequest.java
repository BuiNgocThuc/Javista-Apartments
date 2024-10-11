package com.example.javista.dto.request.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPatchRequest {
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
