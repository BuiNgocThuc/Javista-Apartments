package com.example.javista.dto.request.user;

import com.example.javista.dto.request.PageRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserQueryRequest extends PageRequest {
    String id;

    String username;

    String email;

    String phone;

    String fullName;

    String gender;

    String nationId;

    String userType;

    String dateOfBirth;

    String createdAt;

    String updatedAt;
}
