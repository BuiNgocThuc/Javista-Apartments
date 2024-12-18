package com.example.javista.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.javista.dto.request.item.ItemCreationRequest;
import com.example.javista.dto.request.item.ItemPatchRequest;
import com.example.javista.dto.request.item.ItemUpdateRequest;
import com.example.javista.dto.response.item.ItemResponse;
import com.example.javista.entity.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    // mapping dto creation request to entity
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "image", ignore = true) // Ignore the image field
    @Mapping(target = "isReceive", expression = "java(false)") // Default isReceive to false
    Item creationRequestToEntity(ItemCreationRequest request);

    // mapping dto update request to entity
    @Mapping(target = "user", ignore = true)
    void updateRequestToEntity(@MappingTarget Item item, ItemUpdateRequest request);

    // mapping dto patch request to entity
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void patchRequestToEntity(@MappingTarget Item item, ItemPatchRequest request);

    // mapping entity to dto response
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user", target = "user")
    ItemResponse entityToResponse(Item item);
}
