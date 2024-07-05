package com.kuku.exercise.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;

@ToString
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

    @JsonProperty
    private String optional;

    public void decreaseQuantity(int requestQuantity) {
        this.totalQuantity = this.totalQuantity - requestQuantity;
    }

    public void changeName(String name) {
        this.name = name;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Optional {

        private Boolean returnable;

        private String rateType;

        public boolean isReturnable() {
            if (Objects.isNull(returnable)) {
                return true;
            }
            return false;
        }
    }
}