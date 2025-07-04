package com.somproject.testcontainers_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TestcontainersJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcontainersJavaApplication.class, args);
	}

}
