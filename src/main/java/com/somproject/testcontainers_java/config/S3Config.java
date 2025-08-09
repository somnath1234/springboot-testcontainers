package com.somproject.testcontainers_java.config;

import com.somproject.testcontainers_java.properties.AwsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@EnableConfigurationProperties(AwsProperties.class)
public class S3Config {

    // Configuration for S3 can be added here
    // For example, you can define beans for S3 client, bucket configuration, etc.
    // This is a placeholder for future S3-related configurations.

    @Bean
    public S3Client amazonS3Client(AwsProperties awsProperties) {
        // Create an AmazonS3Client bean using the AWS credentials and region from AwsProperties
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(awsProperties.credentials().accessKey(),
                awsProperties.credentials().secretKey());

        return S3Client.builder().region(Region.of(awsProperties.region()))
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .build();
    }

}
