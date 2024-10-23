package com.example.javista.service;

import com.example.javista.dto.request.setting.SettingPatchRequest;
import com.example.javista.dto.request.setting.SettingUpdateRequest;
import com.example.javista.dto.response.setting.SettingResponse;

public interface SettingService {
        SettingResponse getSettings();

        SettingResponse updateSetting(Integer id, SettingUpdateRequest request);

        SettingResponse patchSetting(Integer id, SettingPatchRequest request);

}
