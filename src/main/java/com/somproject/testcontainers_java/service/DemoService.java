package com.somproject.testcontainers_java.service;

import com.somproject.testcontainers_java.entity.DemoEntity;
import com.somproject.testcontainers_java.repository.DemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    private final DemoRepository demoRepository;

    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public DemoEntity getDemoEntity(Long id) {
        return demoRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public List<DemoEntity> getAll() {
        return demoRepository.findAll();
    }

    public DemoEntity save(DemoEntity demoEntity) {
        return demoRepository.save(demoEntity);
    }
}
