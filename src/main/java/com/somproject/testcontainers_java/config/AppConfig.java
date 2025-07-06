package com.somproject.testcontainers_java.config;

import com.somproject.testcontainers_java.records.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;

import java.util.concurrent.Executors;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
@Slf4j
public class AppConfig {


    private final AppProperties appRecord;

    public AppConfig(AppProperties appRecord) {
        this.appRecord = appRecord;
    }

    @Bean
    public AsyncTaskExecutor applicationTaskExecutor() {
        return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void showAppProperties() {
        log.atInfo()
                .setMessage("User logged in")
                .addKeyValue("Application Name: ", appRecord.name())
                .addKeyValue("Application Version: ", appRecord.version())
                .addKeyValue("Max Threads: ", appRecord.settings().maxThreads())
                .addKeyValue("Debug Mode: ", appRecord.settings().debugMode())
                .log();
        MDC.put("userId", "12345");
        log.info("User action performed");
        MDC.clear();
    }

}
