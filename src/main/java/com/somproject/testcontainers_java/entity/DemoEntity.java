package com.somproject.testcontainers_java.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "demo")
public class DemoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String value;

    public DemoEntity(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DemoEntity() {

    }

    @Override
    public String toString() {
        return "DemoEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
