package com.example.javista.service.media;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    Map uploadFile(MultipartFile file);
}
