package com.example.javista.service;

import com.example.javista.dto.request.item.ItemCreationRequest;
import com.example.javista.dto.request.item.ItemPatchRequest;
import com.example.javista.dto.request.item.ItemQueryRequest;
import com.example.javista.dto.request.item.ItemUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.item.ItemResponse;

public interface ItemService {
    PageResponse<ItemResponse> getItems(ItemQueryRequest query);

    ItemResponse getItemById(Integer id);

    ItemResponse createItem(ItemCreationRequest request);

    ItemResponse updateItem(Integer id, ItemUpdateRequest request);

    ItemResponse patchItem(Integer id, ItemPatchRequest request);

    void deleteItem(Integer id);
}
