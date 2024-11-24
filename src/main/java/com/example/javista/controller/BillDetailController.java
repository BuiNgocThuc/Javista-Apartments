package com.example.javista.controller;

import org.springframework.web.bind.annotation.*;

import com.example.javista.dto.request.billDetail.BillDetailCreationRequest;
import com.example.javista.dto.request.billDetail.BillDetailPatchRequest;
import com.example.javista.dto.request.billDetail.BillDetailQueryRequest;
import com.example.javista.dto.request.billDetail.BillDetailUpdateRequest;
import com.example.javista.dto.response.PageResponse;
import com.example.javista.dto.response.billDetail.BillDetailResponse;
import com.example.javista.service.BillDetailService;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/billDetails")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class BillDetailController {
    BillDetailService billDetailService;

    // query
    @GetMapping
    PageResponse<BillDetailResponse> getBillDetails(@ModelAttribute BillDetailQueryRequest query) {
        return billDetailService.getBillDetails(query);
    }

    // get by id
    @GetMapping("/{id}")
    BillDetailResponse getBillDetailById(@PathVariable Integer id) {
        return billDetailService.getBillDetailById(id);
    }

    // creat
    @PostMapping
    BillDetailResponse createBillDetail(@RequestBody BillDetailCreationRequest request) {
        return billDetailService.createBillDetail(request);
    }

    // update
    @PutMapping("/{id}")
    BillDetailResponse updateBillDetail(@PathVariable Integer id, @RequestBody BillDetailUpdateRequest request) {
        return billDetailService.updateBillDetail(id, request);
    }

    // patch
    @PatchMapping("/{id}")
    BillDetailResponse patchBillDetail(@PathVariable Integer id, @RequestBody BillDetailPatchRequest request) {
        return billDetailService.patchBillDetail(id, request);
    }

    // delete
    @DeleteMapping("/{id}")
    void deleteBillDetail(@PathVariable Integer id) {
        billDetailService.deleteBillDetail(id);
    }
}
