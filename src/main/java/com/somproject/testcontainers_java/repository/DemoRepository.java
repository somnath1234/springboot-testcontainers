package com.somproject.testcontainers_java.repository;

import com.somproject.testcontainers_java.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository extends JpaRepository<DemoEntity, Long> {
}
