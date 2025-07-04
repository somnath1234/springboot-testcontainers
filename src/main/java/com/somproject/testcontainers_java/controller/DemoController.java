package com.somproject.testcontainers_java.controller;

import com.somproject.testcontainers_java.entity.DemoEntity;
import com.somproject.testcontainers_java.entity.JWTResponse;
import com.somproject.testcontainers_java.service.DemoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {


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
    public DemoEntity save(@RequestHeader String jwt, @RequestBody DemoEntity demoEntity) {
        if(jwt.equals("a7X9pQzLk3mVwT2nY8oRj5bCdFgHqWxZ1NvKt6PyMlJr")){
             return demoService.save(demoEntity);
        }
        return null;
    }

    @GetMapping(value = "/jwt")
    public JWTResponse getJwt() {
        JWTResponse jwtResponse = new JWTResponse();
        jwtResponse.setJwt("a7X9pQzLk3mVwT2nY8oRj5bCdFgHqWxZ1NvKt6PyMlJr");
        return jwtResponse;
    }
}
