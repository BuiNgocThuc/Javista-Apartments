package com.example.javista.service.media;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudinaryService {
        Map uploadFile(MultipartFile file);
}
