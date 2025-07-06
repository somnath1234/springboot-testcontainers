package com.somproject.testcontainers_java.records;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app")
public record AppProperties(String name,
                            String version,
                            AppSettings settings) {
    public record AppSettings(
            int maxThreads,
            boolean debugMode
    ) {}
}