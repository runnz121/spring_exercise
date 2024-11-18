package com.kuku.exercise.exe;

import com.kuku.exercise.domain.TestEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MapTest {

    @Test
    void mapTest() {
        List<TestEntity> emptyList = new ArrayList<>();

        Map<Long, TestEntity> toMap = emptyList.stream().collect(Collectors.toMap(TestEntity::getId, Function.identity()));

        boolean b = toMap.containsKey("1");

        System.out.println(b);

        assertThat(toMap).isEqualTo(new HashMap<>());
        assertThat(b).isFalse();
    }
}
