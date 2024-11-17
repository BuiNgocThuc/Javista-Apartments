package com.example.javista.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.javista.dto.request.user.UserCreationRequest;
import com.example.javista.dto.request.user.UserPatchRequest;
import com.example.javista.dto.request.user.UserQueryRequest;
import com.example.javista.dto.request.user.UserUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.user.UserResponse;

public interface UserService {
    @PreAuthorize("hasRole('ADMIN')")
    PageResponse<UserResponse> getUsers(UserQueryRequest query);

    UserResponse getUserById(Integer id);

    UserResponse createUser(UserCreationRequest request);

    UserResponse updateUser(Integer id, UserUpdateRequest request);

    UserResponse patchUser(Integer id, UserPatchRequest request);

    void deleteUser(Integer id);

    UserResponse getMyInfo();

    PageResponse<UserResponse> getUsersByRelationshipRole(UserQueryRequest query, String role);
}
