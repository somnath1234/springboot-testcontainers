package com.somproject.testcontainers_java.controller;

import com.somproject.testcontainers_java.entity.DemoEntity;
import com.somproject.testcontainers_java.service.DemoService;
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
    public DemoEntity save(@RequestBody DemoEntity demoEntity) {
        return demoService.save(demoEntity);
    }
}
