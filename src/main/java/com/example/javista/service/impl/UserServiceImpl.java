package com.example.javista.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.mail.MessagingException;
import jakarta.persistence.criteria.Join;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.javista.dto.request.contact.MailSendRequest;
import com.example.javista.dto.request.contact.SMSSendRequest;
import com.example.javista.dto.request.user.*;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.user.UserResponse;
import com.example.javista.entity.Relationship;
import com.example.javista.entity.User;
import com.example.javista.exception.AppException;
import com.example.javista.exception.ErrorCode;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.UserMapper;
import com.example.javista.repository.UserRepository;
import com.example.javista.service.UserService;
import com.example.javista.service.media.CloudinaryService;
import com.example.javista.service.media.EmailService;
import com.example.javista.service.media.SMSService;
import com.example.javista.utils.QueryUtils;
import com.example.javista.utils.RandomPassword;
import com.example.javista.utils.SecurityUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserMapper userMapper;
    UserRepository userRepository;

    FilterSpecification<User> filterSpecification;

    EmailService mailService;
    CloudinaryService cloudinaryService;
    SMSService smsService;

    SecurityUtils securityUtils;

    @Override
    public PageResponse<UserResponse> getUsers(UserQueryRequest query) {
        // Pagination and Sorting
        Pageable pageable = QueryUtils.getPagination(query);

        // Filtering and searching by specification
        Specification<User> spec = filterSpecification.filteringBySpecification(QueryUtils.getFilterCriterion(query));

        var pageData = userRepository.findAll(spec, pageable);

        return QueryUtils.buildPageResponse(pageData, pageable, userMapper::entityToResponse);
    }

    @Override
    public UserResponse getUserById(Integer id) {
        return userMapper.entityToResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        // check if the username is already exist
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        }
        User user = userMapper.creationRequestToEntity(request);
        String randomPassword = RandomPassword.generateRandomPassword(8);
        // Encrypt the password
        user.setPassword(securityUtils.encryptPassword(randomPassword));
        user.setAvatar("${cloudinary.defaultAvatar}");
        // Save the user
        user = userRepository.save(user);

        // send email to verify the account
        try {
            Map<String, Object> props = new HashMap<>();
            props.put("username", user.getUsername());
            props.put("password", randomPassword);
            props.put("fullName", user.getFullName());

            MailSendRequest mailRequest = MailSendRequest.builder()
                    .to(user.getEmail())
                    .subject("Verify your account")
                    .content("Please verify your account by clicking the link below")
                    .props(props)
                    .build();

            mailService.sendEmail(mailRequest, "ConfirmationEmail");

        } catch (MessagingException e) {
            // handle exception
            e.printStackTrace();
        }

        return userMapper.entityToResponse(user);
    }

    @Override
    public UserResponse updateUser(Integer id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateRequestToEntity(user, request);
        return userMapper.entityToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse patchUser(Integer id, UserPatchRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.patchRequestToEntity(user, request);
        return userMapper.entityToResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        return userMapper.entityToResponse(
                userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public void createPasswordWhenFirstLogin(PasswordCreationRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new AppException(ErrorCode.CONFIRM_PASSWORD_NOT_MATCH);
        }

        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (user.getIsFirstLogin()) {
            user.setPassword(securityUtils.encryptPassword(request.getPassword()));
            user.setIsFirstLogin(false);
            userRepository.save(user);
        }
    }

    @Override
    public UserResponse uploadAvatar(MultipartFile avatar) {
        Map data = cloudinaryService.uploadFile(avatar);
        String secureUrl = (String) data.get("secure_url");
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        user.setAvatar(secureUrl);
        return userMapper.entityToResponse(userRepository.save(user));
    }

    @Override
    public Void notifySmsNewItems(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        // send sms to user
        smsService.sendSMS(SMSSendRequest.builder()
                .phoneNumber(user.getPhone())
                .message("You have new items in your account")
                .build());
        return null;
    }

    @Override
    public void changePassword(PasswordUpdateRequest request) {
        String oldPassword = request.getOldPassword();
        String newPassword = request.getNewPassword();
        String confirmPassword = request.getConfirmPassword();
        if (!newPassword.equals(confirmPassword)) {
            throw new AppException(ErrorCode.CONFIRM_PASSWORD_NOT_MATCH);
        }
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!securityUtils.checkMatchPassword(oldPassword, user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        user.setPassword(securityUtils.encryptPassword(newPassword));
        userRepository.save(user);
    }

    public PageResponse<UserResponse> getUsersByRelationshipRole(UserQueryRequest query, String role) {
        // Pagination and sorting
        Pageable pageable = QueryUtils.getPagination(query);

        // Filtering and searching by specification
        //                List<FilterCriteria> criterion = new ArrayList<>();

        Specification<User> spec = filterSpecification.filteringBySpecification(QueryUtils.getFilterCriterion(query));

        Specification<User> roleSpec = (root, query1, criteriaBuilder) -> {
            Join<User, Relationship> relationshipJoin = root.join("relationships");
            return criteriaBuilder.equal(relationshipJoin.get("role"), role);
        };

        spec = spec.and(roleSpec);

        // Call repository
        var pageData = userRepository.findAll(spec, pageable);

        // Use the utility method to build the PageResponse
        return QueryUtils.buildPageResponse(pageData, pageable, userMapper::entityToResponse);
    }
}
