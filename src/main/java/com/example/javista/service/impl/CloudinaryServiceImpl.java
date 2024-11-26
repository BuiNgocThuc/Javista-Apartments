package com.example.javista.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.example.javista.exception.AppException;
import com.example.javista.exception.ErrorCode;
import com.example.javista.service.media.CloudinaryService;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryServiceImpl implements CloudinaryService {
    Cloudinary cloudinary;

    // upload file by cloudinary
    @Override
    public Map uploadFile(MultipartFile file) {
        try {
            Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
            // get secure url from data
            String secureUrl = (String) data.get("secure_url");
            return data;
        } catch (IOException io) {
            throw new AppException(ErrorCode.FILE_UPLOAD_FAILED);
        }
    }
}
