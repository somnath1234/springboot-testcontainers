package com.somproject.testcontainers_java.service;


import com.somproject.testcontainers_java.config.S3Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3Service {

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
        s3Client.putObject(PutObjectRequest.builder()
                .bucket(bucketName).key(multipartFile.getOriginalFilename()).build(),
                RequestBody.fromBytes(multipartFile.getBytes()));
    }
}
