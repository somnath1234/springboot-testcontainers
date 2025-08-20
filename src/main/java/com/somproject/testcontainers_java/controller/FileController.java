package com.somproject.testcontainers_java.controller;

import com.somproject.testcontainers_java.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController

public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    private final S3Service service;

    public FileController(S3Service service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("File") MultipartFile file) {
        try {
            LOGGER.atInfo().addKeyValue("fileName", file.getName()).log("Start to uploading file to S3");
            service.uploadFile(file);
            LOGGER.atInfo().addKeyValue("fileName", file.getName()).log("Successfully upload file on S3");
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to upload file: " + e.getMessage());
        }
    }
}

