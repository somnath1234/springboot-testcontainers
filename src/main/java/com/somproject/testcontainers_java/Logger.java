package com.somproject.testcontainers_java;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.boot.logging.structured.StructuredLogFormatter;

public class Logger implements StructuredLogFormatter<ILoggingEvent> {

    @Override
    public String format(ILoggingEvent event) {
        StringBuilder logBuilder = new StringBuilder();

        // Basic log details as CSV
        logBuilder.append(event.getTimeStamp()).append(",");
        logBuilder.append(event.getLoggerName()).append(",");
        logBuilder.append(event.getLevel()).append(",");
        logBuilder.append("\"").append(event.getFormattedMessage()).append("\"");

        // Append MDC properties as CSV key=value pairs
        if (event.getMDCPropertyMap() != null) {
            event.getMDCPropertyMap().forEach((key, value) -> {
                logBuilder.append(",").append(key).append("=").append(value);
            });
        }

        logBuilder.append("\n");
        return logBuilder.toString();
    }
}
