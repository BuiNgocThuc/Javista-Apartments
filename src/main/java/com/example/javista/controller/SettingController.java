package com.example.javista.controller;

import org.springframework.web.bind.annotation.*;

import com.example.javista.dto.request.setting.SettingPatchRequest;
import com.example.javista.dto.request.setting.SettingUpdateRequest;
import com.example.javista.dto.response.setting.SettingResponse;
import com.example.javista.service.SettingService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/settings")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SettingController {
    SettingService settingService;

    // test postman Http: http://localhost:8080/javista/settings

    // Update
    @PutMapping("/{id}")
    SettingResponse updateSetting(@PathVariable Integer id, @RequestBody SettingUpdateRequest request) {
        return settingService.updateSetting(id, request);
    }

    // Patch
    @PatchMapping("/{id}")
    void patchSetting(@PathVariable Integer id, @RequestBody SettingPatchRequest request) {
        settingService.patchSetting(id, request);
    }
}
