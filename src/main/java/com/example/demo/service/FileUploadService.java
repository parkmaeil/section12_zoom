package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class FileUploadService {

    @Value("${upload.path}")
    private String uploadPath; // src/main/resources/static/upload/adsdsdsds.png

    public String saveFile(MultipartFile file) throws IOException {
        // Create the directory if it doesn't exist
        Path uploadDir = Paths.get(uploadPath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // Generate a unique filename to handle duplicates
        String originalFilename = file.getOriginalFilename(); // a.png
        String uniqueFilename = generateUniqueFilename(originalFilename); //adsdsdsds.png

        // Save the file
        Path filePath = uploadDir.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFilename; // Return the unique filename
    }

    private String generateUniqueFilename(String originalFilename) {
        // Generate a unique identifier (timestamp or UUID)
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String timestamp = now.format(formatter);
        String randomUUID = UUID.randomUUID().toString().replace("-", ""); // Remove dashes
        System.out.println(timestamp); //
        System.out.println(randomUUID); //

        // Append the .png extension to the filename
        String filenameWithExtension = originalFilename + "-" + timestamp + "-" + randomUUID + ".png";

        return filenameWithExtension;
    }
}
