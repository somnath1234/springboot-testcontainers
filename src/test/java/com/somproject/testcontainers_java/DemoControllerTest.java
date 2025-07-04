package com.somproject.testcontainers_java;

import com.somproject.testcontainers_java.entity.DemoEntity;
import com.somproject.testcontainers_java.repository.DemoRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoControllerTest  {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    DemoRepository demoRepository;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        demoRepository.deleteAll();
    }

    @Test
    void shouldGetAllCustomers() {
        DemoEntity customers =
                new DemoEntity("Dennis");
        demoRepository.save(customers);
        Optional<DemoEntity> entity = demoRepository.findById(customers.getId());
        assertEquals("Dennis", entity.get().getValue());

        // Assume a user with ID 1 exists in test DB or is preloaded
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/demo")
                .then()
                .statusCode(200)
                .body(".", hasSize(1));
        // Assume a user with ID 1 exists in test DB or is preloaded
        ResponseEntity<DemoEntity> response = testRestTemplate.getForEntity("/demo/" + customers.getId(), DemoEntity.class);

        // Assert HTTP status is 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert the response body is not null and has expected properties
        DemoEntity user = response.getBody();
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("Dennis", user.getValue());
    }
}
