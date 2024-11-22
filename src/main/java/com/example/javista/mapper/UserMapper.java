package com.example.javista.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.javista.dto.request.user.UserCreationRequest;
import com.example.javista.dto.request.user.UserPatchRequest;
import com.example.javista.dto.request.user.UserUpdateRequest;
import com.example.javista.dto.response.user.UserResponse;
import com.example.javista.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // mapping dto creation request to entity
    User creationRequestToEntity(UserCreationRequest request);

    // mapping dto update request to entity
    void updateRequestToEntity(@MappingTarget User user, UserUpdateRequest request);

    // mapping dto patch request to entity
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void patchRequestToEntity(@MappingTarget User user, UserPatchRequest request);

    // mapping entity to dto response
    UserResponse entityToResponse(User user);
}
