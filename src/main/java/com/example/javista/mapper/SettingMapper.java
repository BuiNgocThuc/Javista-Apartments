package com.example.javista.mapper;

import com.example.javista.dto.request.setting.SettingCreationRequest;
import com.example.javista.dto.request.setting.SettingPatchRequest;
import com.example.javista.dto.request.setting.SettingUpdateRequest;
import com.example.javista.dto.response.setting.SettingResponse;
import com.example.javista.entity.Setting;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SettingMapper {
        // mapping dto creation request to entity
        Setting creationRequestToEntity(SettingCreationRequest request);

        // mapping dto update request to entity
        void updateRequestToEntity(@MappingTarget Setting setting, SettingUpdateRequest request);

        // mapping dto patch request to entity
        @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
        void patchRequestToEntity(@MappingTarget Setting setting, SettingPatchRequest request);

        // mapping entity to dto response
        SettingResponse entityToResponse(Setting setting);
}
