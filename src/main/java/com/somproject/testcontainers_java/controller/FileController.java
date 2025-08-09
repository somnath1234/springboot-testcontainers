package com.somproject.testcontainers_java.controller;

import com.somproject.testcontainers_java.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    private final S3Service service;

    public FileController(S3Service service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("File") MultipartFile file) {
        try {
            service.uploadFile(file);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to upload file: " + e.getMessage());
        }
    }
}

