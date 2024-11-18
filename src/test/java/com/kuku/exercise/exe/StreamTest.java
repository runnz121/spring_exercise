package com.kuku.exercise.exe;

import com.kuku.exercise.domain.TestEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StreamTest {

    @Test
    void summingIntTest() {

        TestEntity testEntity1 = TestEntity.builder()
                .name("testEntity1")
                .totalQuantity(2)
                .build();

        TestEntity testEntity2 = TestEntity.builder()
                .name("testEntity2")
                .totalQuantity(3)
                .build();

        List<TestEntity> list = List.of(testEntity1, testEntity2);

        Map<String, Integer> map = list.stream().collect(Collectors.groupingBy(TestEntity::getName,
                Collectors.summingInt(TestEntity::getTotalQuantity)));

        assertAll(
                () -> assertThat(map.get("testEntity1")).isEqualTo(2),
                () -> assertThat(map.get("testEntity2")).isEqualTo(3)
        );
    }

    @Test
    void forEachTest() {

        List<Integer> listNums = List.of(1,2,3,4,5,6,7,8,9,10);

        listNums.forEach(
                nums -> {
                    if (nums % 2 == 0) {
                        return;
                    }
                    System.out.println(nums);
                }
        );
    }
}
