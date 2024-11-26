package com.example.javista.controller;

import com.example.javista.dto.request.user.*;
import com.example.javista.dto.response.ApiResponse;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.user.UserResponse;
import com.example.javista.service.UserService;
import com.example.javista.service.media.CloudinaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    CloudinaryService cloudinaryService;

    // test postman Http: http://localhost:8080/javista/users

    // change password
    @PostMapping("/me/update-password")
    ApiResponse<Void> changePassword(@RequestBody PasswordUpdateRequest request) {
        userService.changePassword(request);
        return ApiResponse.<Void>builder()
                .message("Change password Successfully")
                .build();
    }

    // create password when first login
    @PostMapping("/me/first-login")
    ApiResponse<Void> createPasswordWhenFirstLogin(@RequestBody PasswordCreationRequest request) {
        userService.createPasswordWhenFirstLogin(request);
        return ApiResponse.<Void>builder()
            .message("Create password when first login Successfully")
            .build();
    }

    // get my info
    @GetMapping("/me")
    UserResponse getMyInfo() {
        return userService.getMyInfo();
    }

    // upload avatar
    @PostMapping(value = "/me/upload_avatar")
    ApiResponse<UserResponse> uploadAvatar(@RequestParam("avatar") MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or not provided!");
        }
        return ApiResponse.<UserResponse>builder()
                .message("Upload avatar Successfully")
            .result(userService.uploadAvatar(file))
                .build();
    }

    // Query
    @GetMapping
    PageResponse<UserResponse> getUsers(@ModelAttribute UserQueryRequest query) {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", auth.getName());
        auth.getAuthorities().forEach(authority -> log.info("Authority: {}", authority.getAuthority()));

        return userService.getUsers(query);
    }

    // Filtering users by relationship_role [OWNER - USER]
    //        @GetMapping("/{role}")
    //        PageResponse<UserResponse> getUsersByRelationshipRole(
    //                        @ModelAttribute UserQueryRequest query,
    //                        @PathVariable String role) {
    //                return userService.getUsersByRelationshipRole(query, role);
    //        }

    // Query by id
    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // Create
    @PostMapping
    UserResponse createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    // Update
    @PutMapping("/{id}")
    UserResponse updateUser(@PathVariable Integer id, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    // Delete
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    // Patch
    @PatchMapping("/{id}")
    void patchUser(@PathVariable Integer id, @RequestBody UserPatchRequest request) {
        userService.patchUser(id, request);
    }

    // Notify SMS
    @PostMapping("/{id}/notify-sms")
    ApiResponse<Void> notifySmsNewItems(@PathVariable Integer id) {
        userService.notifySmsNewItems(id);
        return ApiResponse.<Void>builder()
                .message("Notify SMS Successfully")
                .build();
    }
}
