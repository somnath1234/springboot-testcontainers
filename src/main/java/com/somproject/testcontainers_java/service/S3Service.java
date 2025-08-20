package com.somproject.testcontainers_java.service;


import com.somproject.testcontainers_java.config.S3Config;
import com.somproject.testcontainers_java.controller.FileController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3Service.class);

    @Value("${s3.bucket.name}")
    private String bucketName;


    private final S3Client s3Client;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    /**
     * Uploads a file to an S3 bucket.
     *
     * @param multipartFile the file to upload
     * @throws IOException if an I/O error occurs
     */

    public void uploadFile(MultipartFile multipartFile) throws IOException {
        LOGGER.atInfo().log("uploadFile : start to upload file");
        s3Client.putObject(PutObjectRequest.builder()
                .bucket(bucketName).key(multipartFile.getOriginalFilename()).build(),
                RequestBody.fromBytes(multipartFile.getBytes()));
    }
}
