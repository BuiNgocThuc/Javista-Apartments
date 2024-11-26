package com.example.javista.dto.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordCreationRequest {
    @NotNull
    String password;

    @NotNull
    String confirmPassword;
}

