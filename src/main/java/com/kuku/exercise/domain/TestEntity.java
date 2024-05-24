package com.kuku.exercise.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestEntity {

    public static final TestEntity EMPTY = new TestEntity();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String value;

    private Integer totalQuantity;

    private Integer usedQuantity;

    public void decreaseQuantity(int requestQuantity) {
        this.totalQuantity = this.totalQuantity - requestQuantity;
    }

    public void changeName(String name) {
        this.name = name;
    }
}