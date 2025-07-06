package com.somproject.testcontainers_java.config;

import io.micrometer.tracing.BaggageInScope;
import io.micrometer.tracing.Tracer;

public class TracingExample {

    private final Tracer tracer;

    public TracingExample(Tracer tracer) {
        this.tracer = tracer;
    }

    public void doSomeWork() {
        // Create baggage to propagate custom context (e.g., userId)
        try (BaggageInScope baggage = tracer.createBaggageInScope("userId", "12345")) {
            // Your business logic here
            System.out.println("Tracing with userId baggage");
        }
    }
}
