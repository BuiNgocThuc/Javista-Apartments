package com.example.javista.service.Implements;

import com.example.javista.dto.request.setting.SettingPatchRequest;
import com.example.javista.dto.request.setting.SettingUpdateRequest;
import com.example.javista.dto.response.setting.SettingResponse;
import com.example.javista.entity.Setting;
import com.example.javista.mapper.SettingMapper;
import com.example.javista.repository.SettingRepository;
import com.example.javista.service.SettingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class SettingServiceImpl implements SettingService {
        SettingMapper settingMapper;
        SettingRepository settingRepository;

        @Override
        public SettingResponse getSetting() {
                return settingMapper.entityToResponse(settingRepository.findAll().get(0))       ;
        }

        @Override
        public SettingResponse updateSetting(Integer id, SettingUpdateRequest request) {
                Setting setting = settingRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Setting Not Found"));

                settingMapper.updateRequestToEntity(setting, request);
                return settingMapper.entityToResponse(settingRepository.save(setting));
        }

        @Override
        public SettingResponse patchSetting(Integer id, SettingPatchRequest request) {
                Setting setting = settingRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Setting Not Found"));

                settingMapper.patchRequestToEntity(setting, request);
                return settingMapper.entityToResponse(settingRepository.save(setting));
        }
}
