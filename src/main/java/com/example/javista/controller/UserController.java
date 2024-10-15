package com.example.javista.controller;

import com.example.javista.dto.request.user.UserCreationRequest;
import com.example.javista.dto.request.user.UserPatchRequest;
import com.example.javista.dto.request.user.UserQueryRequest;
import com.example.javista.dto.request.user.UserUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.user.UserResponse;
import com.example.javista.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
        UserService userService;

        // test postman Http: http://localhost:8080/javista/users

        // Query
        @GetMapping
        PageResponse<UserResponse> getUsers(@RequestParam UserQueryRequest query) {
                return userService.getUsers(query);
        }

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

        //Delete
        @DeleteMapping("/{id}")
        void deleteUser(@PathVariable Integer id) {
                userService.deleteUser(id);
        }

        // Patch
        @PatchMapping("/{id}")
        void patchUser(@PathVariable Integer id, @RequestBody UserPatchRequest request) {
                userService.patchUser(id, request);
        }

}
