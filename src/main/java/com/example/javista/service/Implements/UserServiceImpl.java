package com.example.javista.service.Implements;

import com.example.javista.dto.request.user.UserCreationRequest;
import com.example.javista.dto.request.user.UserPatchRequest;
import com.example.javista.dto.request.user.UserQueryRequest;
import com.example.javista.dto.request.user.UserUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.user.UserResponse;
import com.example.javista.entity.User;
import com.example.javista.mapper.UserMapper;
import com.example.javista.repository.UserRepository;
import com.example.javista.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
        UserMapper userMapper;
        UserRepository userRepository;

        @Override
        public PageResponse<UserResponse> getUsers(UserQueryRequest query) {
                return null;
        }

        @Override
        public UserResponse getUserById(Integer id) {
                return userMapper.entityToResponse(userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("User Not Found")));
        }

        @Override
        public UserResponse createUser(UserCreationRequest request) {
                User user = userMapper.creationRequestToEntity(request);
                return userMapper.entityToResponse(userRepository.save(user));
        }

        @Override
        public UserResponse updateUser(Integer id, UserUpdateRequest request) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("User Not Found"));

                userMapper.updateRequestToEntity(user, request);
                return userMapper.entityToResponse(userRepository.save(user));
        }

        @Override
        public UserResponse patchUser(Integer id, UserPatchRequest request) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("User Not Found"));

                userMapper.patchRequestToEntity(user, request);
                return userMapper.entityToResponse(userRepository.save(user));
        }

        @Override
        public void deleteUser(Integer id) {
                User user = userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("User Not Found"));

                user.setDeletedAt(LocalDateTime.now());
                userRepository.save(user);
        }
}
