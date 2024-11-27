package com.example.javista.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.javista.dto.request.userAnswer.UserAnswerCreationRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerPatchRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerQueryRequest;
import com.example.javista.dto.request.userAnswer.UserAnswerUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.userAnswer.UserAnswerResponse;
import com.example.javista.entity.UserAnswer;
import com.example.javista.exception.AppException;
import com.example.javista.exception.ErrorCode;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.UserAnswerMapper;
import com.example.javista.repository.AnswerRepository;
import com.example.javista.repository.UserAnswerRepository;
import com.example.javista.repository.UserRepository;
import com.example.javista.service.UserAnswerService;
import com.example.javista.utils.QueryUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAnswerServiceImpl implements UserAnswerService {
    UserAnswerMapper userAnswerMapper;
    UserAnswerRepository userAnswerRepository;

    UserRepository userRepository;
    AnswerRepository answerRepository;

    FilterSpecification<UserAnswer> filterSpecification;

    @Override
    public PageResponse<UserAnswerResponse> getUserAnswers(UserAnswerQueryRequest query) {
        // Pagination and Sorting
        Pageable pageable = QueryUtils.getPagination(query);

        // Filtering and searching by specification
        Specification<UserAnswer> spec =
                filterSpecification.filteringBySpecification(QueryUtils.getFilterCriterion(query));

        var pageData = userAnswerRepository.findAll(spec, pageable);

        return QueryUtils.buildPageResponse(pageData, pageable, userAnswerMapper::entityToResponse);
    }

    @Override
    public UserAnswerResponse getUserAnswerById(Integer id) {
        return userAnswerMapper.entityToResponse(
                userAnswerRepository.findById(id).orElseThrow(() -> new RuntimeException("UserAnswer Not Found")));
    }

    @Override
    public UserAnswerResponse createUserAnswer(UserAnswerCreationRequest request) {
        UserAnswer userAnswer = userAnswerMapper.creationRequestToEntity(request);

        userAnswer.setUser(userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));

        userAnswer.setAnswer(answerRepository
                .findById(request.getAnswerId())
                .orElseThrow(() -> new RuntimeException("Answer Not Found")));

        return userAnswerMapper.entityToResponse(userAnswerRepository.save(userAnswer));
    }

    @Override
    public UserAnswerResponse updateUserAnswer(Integer id, UserAnswerUpdateRequest request) {
        UserAnswer userAnswer =
                userAnswerRepository.findById(id).orElseThrow(() -> new RuntimeException("UserAnswer Not Found"));

        userAnswer.setUser(
                userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found")));

        userAnswer.setAnswer(answerRepository
                .findById(request.getAnswerId())
                .orElseThrow(() -> new RuntimeException("Answer Not Found")));

        userAnswerMapper.updateRequestToEntity(userAnswer, request);
        return userAnswerMapper.entityToResponse(userAnswerRepository.save(userAnswer));
    }

    @Override
    public UserAnswerResponse patchUserAnswer(Integer id, UserAnswerPatchRequest request) {
        UserAnswer userAnswer =
                userAnswerRepository.findById(id).orElseThrow(() -> new RuntimeException("UserAnswer Not Found"));

        userAnswerMapper.patchRequestToEntity(userAnswer, request);
        return userAnswerMapper.entityToResponse(userAnswerRepository.save(userAnswer));
    }

    @Override
    public void deleteUserAnswer(Integer id) {
        UserAnswer userAnswer =
                userAnswerRepository.findById(id).orElseThrow(() -> new RuntimeException("UserAnswer Not Found"));

        userAnswer.setDeletedAt(LocalDateTime.now());
        userAnswerRepository.save(userAnswer);
    }
}
