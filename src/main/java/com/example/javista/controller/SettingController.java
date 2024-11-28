package com.example.javista.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.example.javista.dto.request.setting.SettingPatchRequest;
import com.example.javista.dto.request.setting.SettingUpdateRequest;
import com.example.javista.dto.response.ApiResponse;
import com.example.javista.dto.response.setting.SettingResponse;
import com.example.javista.service.SettingService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/settings")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SettingController {
    SettingService settingService;

    // test postman Http: http://localhost:8080/javista/settings

    // get
    @GetMapping
    SettingResponse getSetting() {
        return settingService.getCurrentSetting();
    }

    // Update
    @PutMapping("/{id}")
    ApiResponse<SettingResponse>  updateSetting(@PathVariable Integer id, @RequestBody SettingUpdateRequest request) {
        return ApiResponse.<SettingResponse>builder()
            .message("update setting successfully!")
            .result(settingService.updateSetting(id, request))
            .build();
    }

    // Patch
    @PatchMapping("/{id}")
    ApiResponse<SettingResponse> patchSetting(@PathVariable Integer id, @RequestBody SettingPatchRequest request) {
        return ApiResponse.<SettingResponse>builder()
                .message("patch setting successfully!")
                .result(settingService.patchSetting(id, request))
                .build();
    }

    // change status to Prepayment
    @PostMapping("/transition/prepayment")
    ApiResponse<SettingResponse> updateStatusPrepayment() {
        return ApiResponse.<SettingResponse>builder()
                .message("update status prepayment successfully!")
                .result(settingService.updateStatusPrepayment())
                .build();
    }

    // change status to Payment
    @PostMapping("/transition/payment")
    ApiResponse<SettingResponse> updateStatusPayment() {
        return ApiResponse.<SettingResponse>builder()
                .message("update status payment successfully!")
                .result(settingService.updateStatusPayment())
                .build();
    }

    // change status to Overdue
    @PostMapping("/transition/overdue")
    ApiResponse<SettingResponse> updateStatusOverdue() {
        return ApiResponse.<SettingResponse>builder()
                .message("update status overdue successfully!")
                .result(settingService.updateStatusOverdue())
                .build();
    }

    // change status to Delinquent
    @PostMapping("/transition/delinquent")
    ApiResponse<SettingResponse> updateStatusDelinquent() {
        return ApiResponse.<SettingResponse>builder()
                .message("update status delinquent successfully!")
                .result(settingService.updateStatusDelinquent())
                .build();
    }
}
