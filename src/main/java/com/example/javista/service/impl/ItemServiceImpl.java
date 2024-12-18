package com.example.javista.service.impl;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.javista.dto.request.item.ItemCreationRequest;
import com.example.javista.dto.request.item.ItemPatchRequest;
import com.example.javista.dto.request.item.ItemQueryRequest;
import com.example.javista.dto.request.item.ItemUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.item.ItemResponse;
import com.example.javista.entity.Item;
import com.example.javista.exception.AppException;
import com.example.javista.exception.ErrorCode;
import com.example.javista.filter.FilterSpecification;
import com.example.javista.mapper.ItemMapper;
import com.example.javista.repository.ItemRepository;
import com.example.javista.repository.UserRepository;
import com.example.javista.service.ItemService;
import com.example.javista.service.media.CloudinaryService;
import com.example.javista.service.media.SMSService;
import com.example.javista.utils.QueryUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemServiceImpl implements ItemService {
    ItemMapper itemMapper;
    ItemRepository itemRepository;

    UserRepository userRepository;

    CloudinaryService cloudinaryService;
    SMSService smsService;

    FilterSpecification<Item> filterSpecification;

    @Override
    public PageResponse<ItemResponse> getItems(ItemQueryRequest query) {
        // Pagination and Sorting
        Pageable pageable = QueryUtils.getPagination(query);

        // Filtering and searching by specification
        Specification<Item> spec = filterSpecification.filteringBySpecification(QueryUtils.getFilterCriterion(query));

        var pageData = itemRepository.findAll(spec, pageable);

        return QueryUtils.buildPageResponse(pageData, pageable, itemMapper::entityToResponse);
    }

    @Override
    public ItemResponse getItemById(Integer id) {
        return itemMapper.entityToResponse(
                itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item Not Found")));
    }

    @Override
    public ItemResponse createItem(ItemCreationRequest request) {
        Item item = itemMapper.creationRequestToEntity(request);

        item.setUser(
                userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found")));

        return itemMapper.entityToResponse(itemRepository.save(item));
    }

    @Override
    public ItemResponse updateItem(Integer id, ItemUpdateRequest request) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item Not Found"));

        item.setUser(
                userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found")));

        itemMapper.updateRequestToEntity(item, request);
        return itemMapper.entityToResponse(itemRepository.save(item));
    }

    @Override
    public ItemResponse patchItem(Integer id, ItemPatchRequest request) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item Not Found"));

        itemMapper.patchRequestToEntity(item, request);
        return itemMapper.entityToResponse(itemRepository.save(item));
    }

    @Override
    public void deleteItem(Integer id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item Not Found"));

        item.setDeletedAt(LocalDateTime.now());
        itemRepository.save(item);
    }

    @Override
    public ItemResponse uploadImage(MultipartFile file, Integer id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_FOUND));

        Map data = cloudinaryService.uploadFile(file);
        String secureUrl = (String) data.get("secure_url");

        item.setImage(secureUrl);
        return itemMapper.entityToResponse(itemRepository.save(item));
    }
}
