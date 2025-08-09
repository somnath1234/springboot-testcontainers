package com.somproject.testcontainers_java.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloud.aws")
public record AwsProperties(Credentials credentials, String region) {
    public record Credentials(
        String accessKey,
        String secretKey
    ) {
    }
}
