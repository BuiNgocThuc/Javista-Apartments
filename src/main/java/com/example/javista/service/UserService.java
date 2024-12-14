package com.example.javista.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import com.example.javista.dto.request.user.*;
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

    void changePassword(PasswordUpdateRequest request);

    void createPasswordWhenFirstLogin(PasswordCreationRequest request);

    UserResponse uploadAvatar(MultipartFile avatar);

    Void notifySmsNewItems(Integer id);
}
