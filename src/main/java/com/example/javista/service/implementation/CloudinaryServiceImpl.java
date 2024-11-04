package com.example.javista.service.implementation;

import com.cloudinary.Cloudinary;
import com.example.javista.configuration.CloudinaryConfig;
import com.example.javista.service.media.CloudinaryService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryServiceImpl implements CloudinaryService {
        Cloudinary cloudinary;

        // upload file by cloudinary
        public Map uploadFile(MultipartFile file, String folderName) throws IOException {
                return cloudinary.uploader().upload(file.getBytes(),
                        Map.of("folder", folderName));
        }
}
