package com.example.javista.controller;

import org.springframework.web.bind.annotation.*;

import com.example.javista.dto.request.item.ItemCreationRequest;
import com.example.javista.dto.request.item.ItemPatchRequest;
import com.example.javista.dto.request.item.ItemQueryRequest;
import com.example.javista.dto.request.item.ItemUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.item.ItemResponse;
import com.example.javista.service.ItemService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemController {
    ItemService itemService;

    // test postman Http: http://localhost:8080/javista/items

    // Query
    @GetMapping
    PageResponse<ItemResponse> getItems(@ModelAttribute ItemQueryRequest query) {
        return itemService.getItems(query);
    }

    // Query by id
    @GetMapping("/{id}")
    ItemResponse getItemById(@PathVariable Integer id) {
        return itemService.getItemById(id);
    }

    // Create
    @PostMapping
    ItemResponse createItem(@RequestBody ItemCreationRequest request) {
        return itemService.createItem(request);
    }

    // Update
    @PutMapping("/{id}")
    ItemResponse updateItem(@PathVariable Integer id, @RequestBody ItemUpdateRequest request) {
        return itemService.updateItem(id, request);
    }

    // Delete
    @DeleteMapping("/{id}")
    void deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
    }

    // Patch
    @PatchMapping("/{id}")
    void patchItem(@PathVariable Integer id, @RequestBody ItemPatchRequest request) {
        itemService.patchItem(id, request);
    }
}
