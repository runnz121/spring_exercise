package com.kuku.exercise.exe;

import com.kuku.exercise.domain.TestEntity;
import com.kuku.exercise.domain.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;

@SpringBootTest
public class SortTest {

    @Autowired
    private TestRepository testRepository;

    @Test
    void sortTest() {

        List<TestEntity> testEntities = List.of(
                TestEntity.builder()
                        .name("@32")
                        .build(),
                TestEntity.builder()
                        .name("@35")
                        .build()
        );


        List<TestEntity> all = testRepository.saveAll(testEntities);

        System.out.println(all);

        all.sort(Comparator.comparing(TestEntity::getId, Comparator.reverseOrder()));

        System.out.println(all);
    }
}
