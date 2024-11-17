package com.example.javista.controller;

import org.springframework.web.bind.annotation.*;

import com.example.javista.dto.request.bill.BillCreationRequest;
import com.example.javista.dto.request.bill.BillPatchRequest;
import com.example.javista.dto.request.bill.BillQueryRequest;
import com.example.javista.dto.request.bill.BillUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.bill.BillResponse;
import com.example.javista.service.BillService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillController {
    BillService billService;

    // query
    @GetMapping
    PageResponse<BillResponse> getBills(@ModelAttribute BillQueryRequest query) {
        return billService.getBills(query);
    }

    // get by id
    @GetMapping("/{id}")
    BillResponse getBillById(@PathVariable Integer id) {
        return billService.getBillById(id);
    }

    // create
    @PostMapping
    BillResponse createBill(@RequestBody BillCreationRequest request) {
        return billService.createBill(request);
    }

    // update
    @PutMapping("/{id}")
    BillResponse updateBill(@PathVariable Integer id, @RequestBody BillUpdateRequest request) {
        return billService.updateBill(id, request);
    }

    // patch
    @PatchMapping("/{id}")
    BillResponse patchBill(@PathVariable Integer id, @RequestBody BillPatchRequest request) {
        return billService.patchBill(id, request);
    }

    // delete
    @DeleteMapping("/{id}")
    void deleteBill(@PathVariable Integer id) {
        billService.deleteBill(id);
    }
}
