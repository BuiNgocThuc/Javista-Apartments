package com.example.javista.service.implementation;

import com.example.javista.dto.request.user.UserCreationRequest;
import com.example.javista.dto.request.user.UserPatchRequest;
import com.example.javista.dto.request.user.UserQueryRequest;
import com.example.javista.dto.request.user.UserUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.user.UserResponse;
import com.example.javista.entity.Relationship;
import com.example.javista.entity.User;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.UserMapper;
import com.example.javista.repository.UserRepository;
import com.example.javista.service.UserService;
import com.example.javista.utils.QueryUtils;
import jakarta.persistence.criteria.Join;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
        UserMapper userMapper;
        UserRepository userRepository;

        FilterSpecification<User> filterSpecification;

        @Override
        public PageResponse<UserResponse> getUsers(UserQueryRequest query) {
                // Pagination and Sorting
                Pageable pageable = QueryUtils.getPagination(query);

                //Filtering and searching by specification
                Specification<User> spec = filterSpecification.filteringBySpecification(
                          QueryUtils.getFilterCriterion(query)
                );

                var pageData = userRepository.findAll(spec, pageable);

                return QueryUtils.buildPageResponse(pageData, pageable, userMapper::entityToResponse);

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

        @Override
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
