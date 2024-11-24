package com.example.javista.service;

import com.example.javista.dto.request.user.*;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.user.UserResponse;
import org.springframework.web.multipart.MultipartFile;

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
