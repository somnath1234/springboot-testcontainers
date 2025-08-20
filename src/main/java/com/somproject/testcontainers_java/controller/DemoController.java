package com.somproject.testcontainers_java.controller;

import com.somproject.testcontainers_java.entity.DemoEntity;
import com.somproject.testcontainers_java.entity.JWTResponse;
import com.somproject.testcontainers_java.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }


    @GetMapping("/{id}")
    public DemoEntity getDemoEntity(@PathVariable("id") Long id) {
        return demoService.getDemoEntity(id);
    }

    @GetMapping
    public List<DemoEntity> findAll() {
        return demoService.getAll();
    }

    @PostMapping
    public ResponseEntity<DemoEntity> save(@RequestHeader String jwt, @RequestBody DemoEntity demoEntity) {
        LOGGER.atInfo().addKeyValue("id", demoEntity.getId()).log("request received from create entity");
        if(jwt.equals("a7X9pQzLk3mVwT2nY8oRj5bCdFgHqWxZ1NvKt6PyMlJr")){
             return ResponseEntity.ok(demoService.save(demoEntity));
        }
        LOGGER.atInfo().addKeyValue("id", demoEntity.getId()).log("Entity created successfully");
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/jwt")
    public JWTResponse getJwt() {
        JWTResponse jwtResponse = new JWTResponse();
        jwtResponse.setJwt("a7X9pQzLk3mVwT2nY8oRj5bCdFgHqWxZ1NvKt6PyMlJr");
        return jwtResponse;
    }
}
