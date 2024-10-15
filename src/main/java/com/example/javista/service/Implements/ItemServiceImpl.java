package com.example.javista.service.Implements;

import com.example.javista.dto.request.item.ItemCreationRequest;
import com.example.javista.dto.request.item.ItemPatchRequest;
import com.example.javista.dto.request.item.ItemQueryRequest;
import com.example.javista.dto.request.item.ItemUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.item.ItemResponse;
import com.example.javista.entity.Item;
import com.example.javista.mapper.ItemMapper;
import com.example.javista.repository.ItemRepository;
import com.example.javista.repository.UserRepository;
import com.example.javista.service.ItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemServiceImpl implements ItemService {
        ItemMapper itemMapper;
        ItemRepository itemRepository;

        UserRepository userRepository;

        @Override
        public PageResponse<ItemResponse> getItems(ItemQueryRequest query) {
                return null;
        }

        @Override
        public ItemResponse getItemById(Integer id) {
                return itemMapper.entityToResponse(itemRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Item Not Found")));
        }

        @Override
        public ItemResponse createItem(ItemCreationRequest request) {
                Item item = itemMapper.creationRequestToEntity(request);

                item.setUser(userRepository.findById(request.getUserId())
                                .orElseThrow(() -> new RuntimeException("User Not Found")));

                return itemMapper.entityToResponse(itemRepository.save(item));
        }

        @Override
        public ItemResponse updateItem(Integer id, ItemUpdateRequest request) {
                Item item = itemRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Item Not Found"));

                item.setUser(userRepository.findById(request.getUserId())
                                .orElseThrow(() -> new RuntimeException("User Not Found")));

                itemMapper.updateRequestToEntity(item, request);
                return itemMapper.entityToResponse(itemRepository.save(item));
        }

        @Override
        public ItemResponse patchItem(Integer id, ItemPatchRequest request) {
                Item item = itemRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Item Not Found"));

                itemMapper.patchRequestToEntity(item, request);
                return itemMapper.entityToResponse(itemRepository.save(item));
        }

        @Override
        public void deleteItem(Integer id) {
                Item item = itemRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Item Not Found"));

                item.setDeletedAt(LocalDateTime.now());
                itemRepository.save(item);
        }
}
